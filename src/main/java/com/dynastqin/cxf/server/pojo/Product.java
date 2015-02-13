package com.dynastqin.cxf.server.pojo;


import com.google.common.base.Objects;

/**
 * Created by tantao on 14-9-4.
 */
public class Product {
    private Integer id;
    private String name;
    private String company;

    public Product() {
    }

    public Product(Integer id, String name, String company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString(){
        return Objects.toStringHelper(this)
                .add("id",id)
                .add("name",name)
                .add("company",company)
                .toString();
    }
}
