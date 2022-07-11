package com.woniu.outnet.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClassLiveWatchTotle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long crlid;

    private Long maxnum;

    private Long effectiveviewers;

}
