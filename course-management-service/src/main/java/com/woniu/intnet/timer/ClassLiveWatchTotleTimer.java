package com.woniu.intnet.timer;


import com.woniu.intnet.web.vo.TotelViewLiveVo;
import com.woniu.outnet.dao.pojo.ClassLiveWatchTotle;
import com.woniu.service.impl.ClassLiveWatchServiceImpl;
import com.woniu.service.impl.ClassLiveWatchTotleServiceImpl;
import com.woniu.service.impl.TotleViewLiveVoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于定时更改统计表的状态
 */
@Component
@RequiredArgsConstructor
public class ClassLiveWatchTotleTimer {

    private final TotleViewLiveVoServiceImpl totleViewLiveVoService;

    private final ClassLiveWatchServiceImpl classLiveWatchService;

    private final ClassLiveWatchTotleServiceImpl classLiveWatchTotleService;

    @SneakyThrows
    @Scheduled(fixedDelay = 60000)
    public void UpdateClassLiveWatchTotleByTimer(){
        //查出有多少符合要求的
        List<TotelViewLiveVo> totelViewLiveVos = totleViewLiveVoService.listTotelViewLiveVoWhileUserStatusIsY();
        //没有符合要求的结束线程
        if(totelViewLiveVos.size()<=0||totelViewLiveVos==null){
            return;
        }
        //有符合要求的创建存储的集合
        List<ClassLiveWatchTotle> liveWatchTotles = new ArrayList<>();
        //遍历查出来的对象
        for (TotelViewLiveVo totelViewLiveVo : totelViewLiveVos) {
            //当时第一进来
            if(liveWatchTotles.size()<=0){
                //生成第一个对象存入
                ClassLiveWatchTotle classLiveWatchTotle = new ClassLiveWatchTotle();
                classLiveWatchTotle.setCrlid(totelViewLiveVo.getCrlid());
                classLiveWatchTotle.setEffectiveviewers(1l);
                classLiveWatchTotle.setMaxnum(classLiveWatchService.getTotleByCrlid(totelViewLiveVo.getCrlid()));
                liveWatchTotles.add(classLiveWatchTotle);
            }else{
                //当不是第一次进来
                //创建新的对象
                ClassLiveWatchTotle classLiveWatchTotle = new ClassLiveWatchTotle();
                //存值
                classLiveWatchTotle.setCrlid(totelViewLiveVo.getCrlid());
                //遍历集合
                int firstCount = 0;
                for(ClassLiveWatchTotle liveWatchTotle:liveWatchTotles){
                    //如果有存在的
                    if(liveWatchTotle.getCrlid() == classLiveWatchTotle.getCrlid()){
                        //数量+1
                        liveWatchTotle.setEffectiveviewers(liveWatchTotle.getEffectiveviewers()+1l);
                        firstCount++;
                    }
                }
                //如果没有进入添加,表示没有重复的,就得新增
                if(firstCount==0){
                    classLiveWatchTotle.setCrlid(totelViewLiveVo.getCrlid());
                    classLiveWatchTotle.setEffectiveviewers(1l);
                    classLiveWatchTotle.setMaxnum(classLiveWatchService.getTotleByCrlid(totelViewLiveVo.getCrlid()));
                    liveWatchTotles.add(classLiveWatchTotle);
                }

            }
        }
        //最后修改修改
        for (ClassLiveWatchTotle liveWatchTotle : liveWatchTotles) {
            classLiveWatchTotleService.updateClassLiveWatchTotleByCrlid(liveWatchTotle);
        }

    }
}
