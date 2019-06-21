package com.idiot.blog.common;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @ClassName:BaseEntity
 * @Description:TODO
 * @Version:1.0
 **/
@MappedSuperclass
public class CommonEntity {
    private Integer id;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
