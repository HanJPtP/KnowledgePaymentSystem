package com.woniu.outnet.dao.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class ClassLikesMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long crlid;

    private Long userid;

    private Integer likes;

    private Integer type;
}
