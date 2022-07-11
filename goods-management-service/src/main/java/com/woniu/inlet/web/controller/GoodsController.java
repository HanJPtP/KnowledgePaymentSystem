package com.woniu.inlet.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.inlet.timer.thread.ThreadTaskAutoshelves;
import com.woniu.inlet.web.fo.GoodsSkuAddFo;
import com.woniu.inlet.web.fo.GoodsSkuParamsValueAddFo;
import com.woniu.inlet.web.fo.GoodsSkuParamsValueUpdateFo;
import com.woniu.inlet.web.fo.GoodsSkuUpdateFo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outlet.dao.convert.GoodsSkuToGoodsSkuDto;
import com.woniu.outlet.dao.dto.*;
import com.woniu.outlet.dao.mysql.po.*;
import com.woniu.outlet.dao.repository.GoodsSkuRepository;
import com.woniu.service.impl.GoodsParamsValueServiceImpl;
import com.woniu.service.impl.GoodsServiceImpl;
import com.woniu.service.impl.GoodsSlideShowingImgsServiceImpl;
import com.woniu.service.impl.TimingSaleServiceImpl;
import com.woniu.inlet.timer.thread.RedisTaskRemoveToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
public class GoodsController {

    // 操作 redis
    @Autowired
    private RedisTemplate redisTemplate;

    // 操作 MySQL 数据库
    @Autowired
    private GoodsServiceImpl goodsService;

    @Autowired
    private ScheduledThreadPoolExecutor executor;

    @Autowired
    private SnowFlakeGenerator snowFlakeGenerator;

    @Autowired
    private TimingSaleServiceImpl timingSaleService;

    @Autowired
    private GoodsParamsValueServiceImpl goodsParamsValueService;

    @Autowired
    private GoodsSlideShowingImgsServiceImpl goodsSlideShowingImgsService;

    // 操作 elasticsearch
    @Autowired
    private GoodsSkuRepository goodsSkuRepository;

    /**
     * 分页多条件查询商品列表
     * @param saleable
     * @param title
     * @param startTime
     * @param endTime
     * @param pageno
     * @param pageSize
     * @return
     */
    @GetMapping("/goods/list")
    public ResponseResult listGoodsItem(@RequestParam(value = "saleable", required = false, defaultValue = "") String saleable,
                                        @RequestParam(value = "title", required = false, defaultValue = "") String title,
                                        @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                                        @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
                                        @RequestParam(value = "pageno", required = true, defaultValue = "1") Integer pageno,
                                        @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize) {
        ResponseResult responseResult;
        Page<GoodsSku> goodsSkuPage = goodsSkuRepository.listGoodsSkuDtoByItem(saleable, title, startTime, endTime, pageno, pageSize);

        // 从 elasticsearch 查询, 查询到则直接返回
        if (goodsSkuPage.getContent().size() > 0) {
            PageInfo<GoodsSkuDto> pageInfo = GoodsSkuToGoodsSkuDto.turnGoodsSkuToGoodsSkuDtoPageInfo(goodsSkuPage);
            responseResult  = new ResponseResult(200, "ok", pageInfo);
        } else {
            // 为查询到相关信息, 则从 MySQL 查询是否存在相关信息, 判断是否再保存到 elasticsearch
            IPage<GoodsSku> goodsSkuIPage = goodsService.listAllGoodsSkuItem(saleable, title, startTime, endTime, pageno, pageSize);
            if (goodsSkuIPage.getRecords().size() > 0) {
                goodsSkuRepository.saveAll(goodsSkuIPage.getRecords());
                Page<GoodsSku> newGoodsSkuPage = goodsSkuRepository.listGoodsSkuDtoByItem(saleable, title, startTime, endTime, pageno, pageSize);
                PageInfo<GoodsSkuDto> pageInfo = GoodsSkuToGoodsSkuDto.turnGoodsSkuToGoodsSkuDtoPageInfo(newGoodsSkuPage);
                responseResult  = new ResponseResult(200, "ok", pageInfo);
            } else {
                responseResult = new ResponseResult(500, "未查询到相关信息", null);
            }
        }

        return responseResult;
    }

    /**
     * 显示添加商品详情页, 返回 分级信息
     * @return
     */
    @GetMapping("/goods/showAdd")
    public ResponseResult showGoodsSkuAddItem () {
        List<GoodsSorting> firstMenuGoodsSorting = goodsService.listFirstMenuGoodsSorting();
        ResponseResult responseResult;
        if (firstMenuGoodsSorting.size() > 0)
            responseResult = new ResponseResult(200, "ok", firstMenuGoodsSorting);
        else
            responseResult = new ResponseResult(501, "未查询到菜单信息", null);
        return responseResult;
    }

    /**
     * 获得指定商品分类下的 参数信息
     * @param id
     * @return
     */
    @GetMapping("/goods/showAdd/params")
    public ResponseResult showGoodsSkuAddParams(@RequestParam Long id) {
        ResponseResult responseResult;
        // 获得分类下的参数信息
        List<CategoryToParamsToValueDto> dtoList = goodsService.listAllParamsToValuesById(id);
        if (dtoList.size() > 0) {
            // 生成幂等 token , uuid
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForSet().add("goods-showadd-token", uuid);
            System.out.println("the goods-showadd-token uuid is:" + uuid);
            Map map = new HashMap();
            map.put("dtoList", dtoList);
            map.put("goods-showadd-token", uuid);
            // 设置一个线程池延时执行任务, 一小时后删除 token
            RedisTaskRemoveToken task = new RedisTaskRemoveToken(uuid, "goods-showadd-token", redisTemplate);
            executor.schedule(task, 60, TimeUnit.MINUTES);
            responseResult = new ResponseResult(200, "ok", map);
        } else {
            responseResult = new ResponseResult(501, "未查询到相关分类的参数信息", null);
        }
        return responseResult;
    }

    /**
     * 添加商品
     * @return
     */
    @PostMapping("/goods/add")
    public ResponseResult saveGoodsItem(@RequestBody GoodsSkuAddFo goodsSkuAddFo) {
        ResponseResult responseResult;
        // 从 redis 中删除 uuid
        String token = goodsSkuAddFo.getGoodsShowaddToken();
        Long l = redisTemplate.opsForSet().remove("goods-showadd-token", token);
        if (l <= 0) {
            responseResult = new ResponseResult(402, "添加失败, 请勿重复提交", null);
            return responseResult;
        }
        List<GoodsSkuParamsValueAddFo> list = goodsSkuAddFo.getGoodsSkuParamsValueList();
        // 保存 goods sku 数组
        List<GoodsSku> goodsSkuList = new ArrayList<>();
        // 添加 goods_sku 的 ID 数组, 用于添加 goods_sku_spu 数据
        List<Long> goodsSkuIdList = new ArrayList<>();
        // 添加 goods_sku_value 数组
        List<GoodsSkuValue> goodsSkuValueList = new ArrayList<>();
        // 添加定时上架 timing_sale 数组
        List<TimingSale> timingSaleList = new ArrayList<>();
        // 添加商品 goods_sku_slideshowingimgs 对象轮播图数组
        List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList = new ArrayList<>();
        // 将 goodsskuaddfo 中的 slideShowingImgs 字符串转为一个字符串数组
        String slideImgsStr = goodsSkuAddFo.getSlideShowingImgs();
        List<String> slideImgsList = new ArrayList<>();
        // 生成一个线程池延时任务列表
        List<ThreadTaskAutoshelves> taskList = new ArrayList<>();
        if (!slideImgsStr.equals("") && slideImgsStr.length() > 0) {
            slideImgsList = Arrays.asList(slideImgsStr.split(",")).stream().collect(Collectors.toList());
        }
        Long goodsSkuId; // goods_sku ID
        Long timingsaleId; // 定时上架表 ID
        for (GoodsSkuParamsValueAddFo fo : list) {
            // 生成 goods_sku ID
            goodsSkuId = snowFlakeGenerator.nextId();
            // 添加 goods_sku 数据
            // 生成 goods_sku 对象
            GoodsSku goodsSku = new GoodsSku();
            goodsSku.setId(goodsSkuId);
            goodsSku.setTitle(goodsSkuAddFo.getTitle());
            goodsSku.setInventory(fo.getInventory());
            goodsSku.setImages(goodsSkuAddFo.getImages());
            goodsSku.setDescription(goodsSkuAddFo.getDescription());
            goodsSku.setGooddetails(goodsSkuAddFo.getGoodsdetails());
            goodsSku.setPrice(fo.getPrice());
            goodsSku.setMarketprice(fo.getMarketprice());
            goodsSku.setOrderno(goodsSkuAddFo.getOrderno());
            goodsSku.setMinnum(fo.getMinnum());
            goodsSku.setMaxnum(fo.getMaxnum());
            goodsSku.setSaleable(fo.getSaleable());
            if (fo.getSaleable().equals("0"))
                goodsSku.setValid("y");
            else
                goodsSku.setValid("n");
            // 设置创建时间为当前时间
            goodsSku.setCreateTime(new Date());
            goodsSku.setIsDeleted("n");
            // 判断是否添加到定时上架表中
            if (fo.getSaleable().equals("1")) {
                timingsaleId = snowFlakeGenerator.nextId();
                TimingSale timingSale = new TimingSale(timingsaleId, goodsSkuId, fo.getStarttime(), fo.getEndtime());
                timingSaleList.add(timingSale);
                // 定时上架时间 starttime 和 endtime 有两种情况
                // 1. starttime 和 endtime 都不为空, 则设置两个定时任务, 定时上架和定时下架, 且下架完成后删除 timingsale 表中该数据
                // 2. starttime 不为空, endtime 为空, 则设置一个定时任务
                if (!fo.getStarttime().equals("") && fo.getStarttime() != null && !fo.getEndtime().equals("") && fo.getEndtime() != null) {
                    // 计算开始上架时间距离现在差值
                    Date date = new Date();
                    Long startMilliSeconds = fo.getStarttime().getTime() - date.getTime();
                    Long endMilliSeconds = fo.getEndtime().getTime() - date.getTime();
                    ThreadTaskAutoshelves y = new ThreadTaskAutoshelves("y", timingsaleId, -1L, goodsService, null);
                    ThreadTaskAutoshelves n = new ThreadTaskAutoshelves("n",timingsaleId,  endMilliSeconds, goodsService, timingSaleService);
                    System.out.println("距离上架时间: " + startMilliSeconds);
                    System.out.println("距离下架时间: " + endMilliSeconds);
                    // 添加到任务列表中
                    taskList.add(y);
                    taskList.add(n);
                } else if (!fo.getStarttime().equals("") && fo.getStarttime() != null && (fo.getEndtime().equals("")) || fo.getEndtime() != null) {
                    Date date = new Date();
                    Long startMilliSeconds = fo.getStarttime().getTime() - date.getTime();
                    ThreadTaskAutoshelves n = new ThreadTaskAutoshelves("n",timingsaleId, startMilliSeconds, goodsService, timingSaleService);
                    taskList.add(n);
                }
            }
            // 添加 goods_sku 对象到其数组
            goodsSkuList.add(goodsSku);
            // 添加 goods_sku_spu 数据
            goodsSkuIdList.add(goodsSkuId);
            // 添加 goods_sku_value 数据
            // 先获取 value ID
            String valueIdsStr = fo.getValueids();
            if (!valueIdsStr.equals("") && valueIdsStr.length() > 0) {
                // 将字符串转为 Long 数组集合
                List<Long> idList = Arrays.asList(valueIdsStr.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
                // 生成goods_ske_value 的 ID
                Long gsvid;
                // 循环生成添加 sku_value 对象
                for (Long id : idList) {
                    gsvid = snowFlakeGenerator.nextId();
                    GoodsSkuValue goodsSkuValue = new GoodsSkuValue(gsvid, goodsSkuId, id);
                    goodsSkuValueList.add(goodsSkuValue);
                }

            } else {
                responseResult = new ResponseResult(401, "产品参数错误, 请选择正确的参数", null);
                return responseResult;
            }
            // 生成 goodssku_slideshowingimgs 对象
            if (slideImgsList.size() > 0) {
                for (String url : slideImgsList) {
                    GoodsSkuSlideshowingimgs goodsSkuSlideshowingimgs = new GoodsSkuSlideshowingimgs(goodsSkuId, url);
                    goodsSkuSlideshowingimgsList.add(goodsSkuSlideshowingimgs);
                }
            }
        }
        int i = goodsService.saveGoodsSkuList(goodsSkuList, goodsSkuIdList, goodsSkuValueList, goodsSkuAddFo.getSpgid(), timingSaleList, goodsSkuSlideshowingimgsList);
        // 更新 elasticsearch 中的数据
        goodsSkuRepository.saveAll(goodsSkuList);
        // 执行线程池延时任务列表中的任务
        if (taskList.size() > 0) {
            for (ThreadTaskAutoshelves task : taskList)
                executor.schedule(task, task.getDelayMilliSeconds(), TimeUnit.MILLISECONDS);
        }
        if (i > 0)
            responseResult = new ResponseResult(200, "ok", null);
        else
            responseResult = new ResponseResult(502, "添加失败", null);
        return responseResult;
    }

    /**
     * 显示商品更新界面所有的信息
     * @param id
     * @return
     */
    @Transactional
    @PostMapping("/goods/{id}/showUpdate")
    public ResponseResult showGoodsSkuUpdateItem(@PathVariable("id") Long id) {
        // 所需信息
        // 所有商品分级信息
        // 当前商品分级信息, goods_sorting 信息,
        // 当前商品 goods_sku 信息
        // 所有当前分类级别下的参数信息以及参数值信息
        // 当前商品所有参数和参数值信息 goods_sku_value 信息 categorytoparamstovaluedto 信息
        // 商品上架时间信息
        // 商品轮播图列表
        ResponseResult responseResult;
        // 查询商品分级信息
        List<GoodsSorting> firstMenuGoodsSorting = goodsService.listFirstMenuGoodsSorting();
        GoodsSorting goodsSorting = goodsService.getGoodsSortingItemByGoodsSkuId(id);
        // 查询商品 goods_sku 详细信息
        GoodsSkuShowUpdateDto goodsSkuShowUpdateDto = goodsService.getGoodsSkuShowaUpdateItemById(id);
        List<GoodsSkuShowUpdateDto> goodsSkuShowUpdateDtos = goodsService.listGoodsSkuShowaUpdateItemById(id);
        // 获得当前商品所有的参数和参数值
        List<GoodsParamsAndIdShowUpdateDto> goodsParamsAndIdShowUpdateDtoList = goodsService.listGoodsSkuOwnedParamsAndValueItem(id);
        // 通过当前商品 goods_sku id 获得当前分类下的所有参数和参数值
        List<CategoryToParamsToValueDto> categoryToParamsToValueDtos = goodsService.listGoodsSkuParamsAndValueByGoodsSkuId(id);
        // 查看当前商品上架类型, 如果是 "1", 即定时上架, 则获得该商品的定时上架时间
        TimingSale timingSale = null;
        if (goodsSkuShowUpdateDto.getSaleable().equals("1")) {
            timingSale = timingSaleService.getTimingSaleByGoodsSkuId(id);
        }
        // 通过商品 goods_sku ID 获得商品轮播图列表
        List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList = goodsSlideShowingImgsService.listGoodsSlideShowingImgsByGoodsSkuId(id);
        if (goodsSkuShowUpdateDto != null) {
            // 生成幂等 token , uuid
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForSet().add("goods-showupdate-token", uuid);
            System.out.println("the goods-showupdate-token uuid is :" + uuid);
            Map map = new HashMap();
            map.put("firstMenuGoodsSorting", firstMenuGoodsSorting);
            map.put("goodsSorting", goodsSorting);
            map.put("goodsSkuShowUpdateDto", goodsSkuShowUpdateDto);
            map.put("goodsSkuShowUpdateDtos", goodsSkuShowUpdateDtos);
            map.put("goodsParamsAndIdShowUpdateDtoList", goodsParamsAndIdShowUpdateDtoList);
            map.put("categoryToParamsToValueDtos", categoryToParamsToValueDtos);
            map.put("timingSale", timingSale);
            map.put("goodsSkuSlideshowingimgsList", goodsSkuSlideshowingimgsList);
            map.put("goods-showupdate-token", uuid);
            // 设置一个线程池延时执行任务, 一小时后删除 token
            RedisTaskRemoveToken task = new RedisTaskRemoveToken(uuid, "goods-showadd-token", redisTemplate);
            ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
            executor.schedule(task, 60, TimeUnit.MINUTES);

            responseResult = new ResponseResult(200, "ok", map);
        } else
            responseResult = new ResponseResult(501, "未查询到相关信息", null);
        return responseResult;
    }

    /**
     * 更新商品信息
     * @return
     */
    @Transactional
    @PostMapping("/goods/update")
    public ResponseResult updateGoodsItem(@RequestBody GoodsSkuUpdateFo goodsSkuUpdateFo) {
        // 删除原来 goods_sku_spu 绑定信息
        // 添加一条 goods_sku_spu 绑定数据
        // 更改 goods_sku 信息
        // 删除 goods_sku_slideshowingimgs 绑定信息
        // 添加 goods_sku_slideshowingimgs 绑定信息
        // 删除 goods_sku_value 当前绑定的 参数
        // 添加 goods_sku_value 绑定参数信息
        // 删除定时任务
        // 添加定时任务
        ResponseResult responseResult;
        // 从 redis 中删除 uuid
        String token = goodsSkuUpdateFo.getGoodsShowUpdateToken();
        Long l = redisTemplate.opsForSet().remove("goods-showupdate-token", token);
        if (l <= 0) {
            responseResult = new ResponseResult(402, "修改失败, 请勿重复提交", null);
            return responseResult;
        }
        // 获得 goods_sku_updatefo
        List<GoodsSkuParamsValueUpdateFo> list = goodsSkuUpdateFo.getGoodsSkuParamsValueList();
        System.out.println(goodsSkuUpdateFo);
        // 保存 goods sku 数组
        List<GoodsSku> goodsSkuList = new ArrayList<>();
        // 添加 goods_sku 的 ID 数组, 用于添加 goods_sku_spu 数据
        List<Long> goodsSkuIdList = new ArrayList<>();
        // 添加 goods_sku_value 数组
        List<GoodsSkuValue> goodsSkuValueList = new ArrayList<>();
        // 添加定时上架 timing_sale 数组
        List<TimingSale> timingSaleList = new ArrayList<>();
        // 添加商品 goods_sku_slideshowingimgs 对象轮播图数组
        List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList = new ArrayList<>();
        // 将 goodsskuaddfo 中的 slideShowingImgs 字符串转为一个字符串数组
        String slideImgsStr = goodsSkuUpdateFo.getSlideShowingImgs();
        List<String> slideImgsList = new ArrayList<>();
        if (!slideImgsStr.equals("") && slideImgsStr.length() > 0) {
            slideImgsList = Arrays.asList(slideImgsStr.split(",")).stream().collect(Collectors.toList());
        }
        Long goodsSkuId; // goods_sku ID
        Long timingsaleId; // 定时上架表 ID
        for (GoodsSkuParamsValueUpdateFo fo : list) {
            // 添加 goods_sku 数据
            // 生成 goods_sku 对象
            GoodsSku goodsSku = new GoodsSku();
            goodsSku.setId(fo.getId());
            goodsSku.setTitle(goodsSkuUpdateFo.getTitle());
            goodsSku.setInventory(fo.getInventory());
            goodsSku.setImages(goodsSkuUpdateFo.getImages());
            goodsSku.setDescription(goodsSkuUpdateFo.getDescription());
            goodsSku.setGooddetails(goodsSkuUpdateFo.getGoodsdetails());
            goodsSku.setPrice(fo.getPrice());
            goodsSku.setMarketprice(fo.getMarketprice());
            goodsSku.setOrderno(goodsSkuUpdateFo.getOrderno());
            goodsSku.setMinnum(fo.getMinnum());
            goodsSku.setMaxnum(fo.getMaxnum());
            goodsSku.setSaleable(fo.getSaleable());
            if (fo.getSaleable().equals("0"))
                goodsSku.setValid("y");
            else
                goodsSku.setValid("n");
            // 设置创建时间为当前时间
            goodsSku.setCreateTime(new Date());
            goodsSku.setIsDeleted("n");
            // 判断是否添加到定时上架表中
            if (fo.getSaleable().equals("1")) {
                timingsaleId = snowFlakeGenerator.nextId();
                TimingSale timingSale = new TimingSale(timingsaleId, fo.getId(), fo.getStarttime(), fo.getEndtime());
                timingSaleList.add(timingSale);
            }
            // 添加 goods_sku 对象到其数组
            goodsSkuList.add(goodsSku);
            // 添加 goods_sku_spu 数据
            goodsSkuIdList.add(fo.getId());
            // 添加 goods_sku_value 数据
            // 先获取 value ID
            String valueIdsStr = fo.getValueids();
            if (!valueIdsStr.equals("") && valueIdsStr.length() > 0) {
                // 将字符串转为 Long 数组集合
                List<Long> idList = Arrays.asList(valueIdsStr.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
                // 生成goods_ske_value 的 ID
                Long gsvid;
                // 循环生成添加 sku_value 对象
                for (Long id : idList) {
                    gsvid = snowFlakeGenerator.nextId();
                    GoodsSkuValue goodsSkuValue = new GoodsSkuValue(gsvid, fo.getId(), id);
                    goodsSkuValueList.add(goodsSkuValue);
                }
            } else {
                responseResult = new ResponseResult(401, "产品参数错误, 请选择正确的参数", null);
                return responseResult;
            }
            // 生成 goodssku_slideshowingimgs 对象
            if (slideImgsList.size() > 0) {
                for (String url : slideImgsList) {
                    GoodsSkuSlideshowingimgs goodsSkuSlideshowingimgs = new GoodsSkuSlideshowingimgs(fo.getId(), url);
                    goodsSkuSlideshowingimgsList.add(goodsSkuSlideshowingimgs);
                }
            }
        }
        int i = goodsService.updateGoodsSkuList(goodsSkuList, goodsSkuIdList, goodsSkuValueList, goodsSkuUpdateFo.getSpgid(), timingSaleList, goodsSkuSlideshowingimgsList);
        // 查询需要在 MySQL 中更新的 skulist , 并更新到 elasticsearch 中
        List<GoodsSku> skuList = goodsService.listGoodsSkuByIds(goodsSkuIdList);
        goodsSkuRepository.saveAll(skuList);
        if (i > 0)
            responseResult = new ResponseResult(200, "ok", null);
        else
            responseResult = new ResponseResult(502, "更新失败", null);
        return responseResult;
    }

    /**
     * 更改商品下架状态
     * @param id
     * @return
     */
    @PostMapping("/goods/{id}/pullOff")
    public ResponseResult soldOutGoodsById(@PathVariable("id") Long id) {
        ResponseResult responseResult = null;
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setId(id);
        goodsSku.setSaleable("1");
        int i = goodsService.pullOffGoodsSkuItem(goodsSku);
        if (i > 0) {
            // 查询需要在 MySQL 中更新的 skulist , 并更新到 elasticsearch 中
            GoodsSku goodsSku1 = goodsService.getGoodsSkuById(id);
            goodsSkuRepository.save(goodsSku1);
            responseResult = new ResponseResult(200, "ok", null);
        }
        else
            responseResult = new ResponseResult(501, "下架失败", null);
        return responseResult;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @PostMapping("/goods/{id}/delete")
    public ResponseResult removeGoodsById(@PathVariable("id") Long id) {
        ResponseResult responseResult;
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setId(id);
        goodsSku.setIsDeleted("y");
        int i = goodsService.deleteGoodsSkuById(goodsSku);
        if (i > 0) {
            // 查询需要在 MySQL 中更新的 skulist , 并更新到 elasticsearch 中
            GoodsSku goodsSku1 = goodsService.getGoodsSkuById(id);
            goodsSkuRepository.save(goodsSku1);
            responseResult = new ResponseResult(200, "ok", null);
        }
        else
            responseResult = new ResponseResult(501, "删除失败", null);
        return responseResult;
    }

    /**
     * 通过 ID数组 获得一个商品信息的  数组, 给订单调用 API
     * @param ids
     * @return
     */
    @GetMapping("/goods/goodsSkuList")
    public ResponseResult<List<OrderGoodsSkuItemDto>> listGoodsSkuListByIdList(@RequestParam(value = "ids", required = true, defaultValue = "") String ids) {
        ResponseResult responseResult;
        if (!ids.equals("") && ids.length() > 0) {
            List<Long> idList = Arrays.asList(ids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
            List<OrderGoodsSkuItemDto> dtoList = goodsService.listGoodsSkuListByIdList(idList);
            if (dtoList.size() > 0)
                responseResult = new ResponseResult(200, "ok", dtoList);
            else
                responseResult = new ResponseResult(501, "未查询到相关信息", null);
        } else
            responseResult = new ResponseResult(401, "参数错误", null);
        return responseResult;
    }

    /**
     * 通过 ID 获得指定商品信息, 给 订单 调用的 API
     * @param id
     * @return
     */
    @GetMapping("/goods/goodsSkuItem")
    public ResponseResult<OrderGoodsSkuItemDto> listGoodsSkuItemById(@RequestParam("id") Long id) {
        ResponseResult responseResult;
        OrderGoodsSkuItemDto orderGoodsSkuItemDto = goodsService.getGoodsSkuItemById(id);
        responseResult = new ResponseResult(200, "ok", orderGoodsSkuItemDto);
        return responseResult;
    }

    /**
     * 为参数添加一条参数值
     * @param value
     * @param sppid
     * @return
     */
    @PostMapping("/goods/values/add")
    public ResponseResult saveParamsValues(@RequestParam("value") String value,
                                           @RequestParam("spp_id") Long sppid) {
        ResponseResult responseResult;
        long nextId = snowFlakeGenerator.nextId();
        GoodsParamsValue goodsParamsValue = new GoodsParamsValue(nextId, sppid, value);
        int i = goodsParamsValueService.saveGoodsParamsValueItem(goodsParamsValue);
        if (i > 0)
            responseResult = new ResponseResult(200, "ok", null);
        else
            responseResult = new ResponseResult(502, "添加参数失败", null);
        return responseResult;
    }
}
