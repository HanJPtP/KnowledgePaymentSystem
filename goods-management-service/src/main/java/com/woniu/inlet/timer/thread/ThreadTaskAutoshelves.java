package com.woniu.inlet.timer.thread;

import com.woniu.service.impl.GoodsServiceImpl;
import com.woniu.service.impl.TimingSaleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;

@Data
@AllArgsConstructor
public class ThreadTaskAutoshelves implements Callable {

    private String status;
    private Long timingsaleId;
    private Long delayMilliSeconds;
    private GoodsServiceImpl goodsService;
    private TimingSaleServiceImpl timingSaleService;

    @Override
    public Object call() throws Exception {
        goodsService.updateGoodsSkuValidItem(timingsaleId, status);
        System.out.println("执行完 goodsservice");
        if (timingSaleService != null) {
            timingSaleService.removeTimingSaleById(timingsaleId);
            System.out.println("执行完 timing_sale");
        }
        return null;
    }
}
