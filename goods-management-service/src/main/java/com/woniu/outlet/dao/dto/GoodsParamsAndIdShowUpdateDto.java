package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsParamsAndIdShowUpdateDto {

    private Long paramsid;
    private String name;
    private Long valueid;
    private String value;

}
