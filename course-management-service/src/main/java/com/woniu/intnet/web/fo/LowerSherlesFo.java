package com.woniu.intnet.web.fo;

import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import com.woniu.service.impl.ClassCurrencyMsgServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

public class LowerSherlesFo implements Runnable {

    private ClassCurrencyMsgServiceImpl classCurrencyMsgService;

    private ClassCurrencyMsg classCurrencyMsg;

    public LowerSherlesFo(ClassCurrencyMsgServiceImpl classCurrencyMsgService, ClassCurrencyMsg classCurrencyMsg) {
        this.classCurrencyMsg = classCurrencyMsg;
        this.classCurrencyMsgService = classCurrencyMsgService;
    }

    @SneakyThrows
    @Override
    public void run() {
        ClassCurrencyMsg classCurrencyMsg = this.classCurrencyMsgService.listClassCurrencyMsgById(this.classCurrencyMsg.getId());
        if (classCurrencyMsg.getStatus().equals("2")) {
            return;
        }
        if (classCurrencyMsg.getEndtime().equals(this.classCurrencyMsg.getEndtime())) {
            this.classCurrencyMsg.setOffshelftime(this.classCurrencyMsg.getEndtime());
            this.classCurrencyMsg.setEndtime(null);
            this.classCurrencyMsg.setStatus("2");
            ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo = new ClassCurrencyMsgUpdateFo();
            BeanUtils.copyProperties(this.classCurrencyMsg, classCurrencyMsgUpdateFo);
            this.classCurrencyMsgService.updateClassCurrencyMsgAllCanNull(classCurrencyMsgUpdateFo);
        }
    }
}
