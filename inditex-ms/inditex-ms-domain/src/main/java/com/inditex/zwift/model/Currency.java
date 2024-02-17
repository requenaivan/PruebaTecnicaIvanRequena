package com.inditex.zwift.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity to managmanent currencies
 */
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    private Long id;

    private String iso;

    private Integer num;

    private String name;

    public Currency() {
    }

    public Currency(Long id, String iso, Integer num, String name) {
        this.id = id;
        this.iso = iso;
        this.num = num;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
