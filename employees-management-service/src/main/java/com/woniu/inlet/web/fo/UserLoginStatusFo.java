package com.woniu.inlet.web.fo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginStatusFo {

    @TableField(value = "uid")
    private Long id;

    private String status;

}
