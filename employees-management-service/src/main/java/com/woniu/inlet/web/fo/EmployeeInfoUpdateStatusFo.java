package com.woniu.inlet.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoUpdateStatusFo {

    private Long eid;
    private String status;

}
