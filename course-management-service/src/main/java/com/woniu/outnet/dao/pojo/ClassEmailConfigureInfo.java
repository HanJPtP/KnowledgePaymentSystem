package com.woniu.outnet.dao.pojo;


import com.woniu.intnet.web.vo.EmailConfigInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClassEmailConfigureInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    private String mailhost;

    private Integer mailport;

    private String mailusername;

    private String mailpassword;

    private String mailprotocol;

    private String mailfrom;


    public void setEmailConfigInfoVoToThis(EmailConfigInfoVo emailConfigInfoVo){
        this.setMailfrom(emailConfigInfoVo.getMailfrom());
        this.setMailhost(emailConfigInfoVo.getMailhost());
        this.setMailport(emailConfigInfoVo.getMailport());
        this.setMailpassword(emailConfigInfoVo.getMailpassword());
        this.setMailprotocol(emailConfigInfoVo.getMailprotocol());
        this.setMailusername(emailConfigInfoVo.getMailusername());
    }
}
