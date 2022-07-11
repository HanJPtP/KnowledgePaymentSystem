package com.woniu.outnet.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCurrencyWatch {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    private Long crlid;

    private Long userid;

    private Date firsttime;

    private Date quittime;

    private Long watchtime;
    @TableField(exist = false)
    private ClassCurrencyMsg classCurrencyMsg;

    public WatchUserTimeByCurrencyVo MakeClassCurrencyWatchToWatchUserTimeByCurrencyVo(ClassCurrencyWatch classCurrencyWatch){
        return new WatchUserTimeByCurrencyVo(classCurrencyWatch.getClassCurrencyMsg().getId(),
                classCurrencyWatch.getClassCurrencyMsg().getId(),
                classCurrencyWatch.getUserid(),
                classCurrencyWatch.getWatchtime(),
                classCurrencyWatch.getClassCurrencyMsg().getImg(),
                classCurrencyWatch.getClassCurrencyMsg().getName(),
                classCurrencyWatch.getClassCurrencyMsg().getUserid(),
                classCurrencyWatch.getClassCurrencyMsg().getCcid(),
                classCurrencyWatch.getClassCurrencyMsg().getDescribes(),
                classCurrencyWatch.getClassCurrencyMsg().getStatus(),
                classCurrencyWatch.getClassCurrencyMsg().getCstatus(),
                classCurrencyWatch.getClassCurrencyMsg().getAddress(),
                classCurrencyWatch.getClassCurrencyMsg().getAudition(),
                classCurrencyWatch.getClassCurrencyMsg().getEnclosure(),
                classCurrencyWatch.getClassCurrencyMsg().getOrdernum(),
                classCurrencyWatch.getClassCurrencyMsg().getPrivce(),
                classCurrencyWatch.getClassCurrencyMsg().getStock(),
                classCurrencyWatch.getClassCurrencyMsg().getGroundingtime(),
                classCurrencyWatch.getClassCurrencyMsg().getOffshelftime(),
                classCurrencyWatch.getClassCurrencyMsg().getStarttime(),
                classCurrencyWatch.getClassCurrencyMsg().getEndtime());
    }
}
