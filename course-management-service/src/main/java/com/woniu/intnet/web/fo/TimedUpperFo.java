package com.woniu.intnet.web.fo;

import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import com.woniu.service.impl.ClassCurrencyMsgServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

public class TimedUpperFo implements Runnable {

    private ClassCurrencyMsgServiceImpl classCurrencyMsgService;

    private ClassCurrencyMsg classCurrencyMsg;

    public TimedUpperFo(ClassCurrencyMsgServiceImpl classCurrencyMsgService, ClassCurrencyMsg classCurrencyMsg) {
        this.classCurrencyMsg = classCurrencyMsg;
        this.classCurrencyMsgService = classCurrencyMsgService;
    }

    @SneakyThrows
    @Override
    public void run() {
        ClassCurrencyMsg classCurrencyMsg = this.classCurrencyMsgService.listClassCurrencyMsgById(this.classCurrencyMsg.getId());
        if (classCurrencyMsg.getStatus().equals("1")) {
            return;
        }
        if (classCurrencyMsg.getStarttime().equals(this.classCurrencyMsg.getStarttime())) {
            this.classCurrencyMsg.setGroundingtime(this.classCurrencyMsg.getStarttime());
            this.classCurrencyMsg.setStarttime(null);
            this.classCurrencyMsg.setStatus("1");
            ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo = new ClassCurrencyMsgUpdateFo();
            BeanUtils.copyProperties(this.classCurrencyMsg, classCurrencyMsgUpdateFo);
            this.classCurrencyMsgService.updateClassCurrencyMsgAllCanNull(classCurrencyMsgUpdateFo);
        }
    }
}
