package com.dynastqin.cxf.server.impl;

import com.dynastqin.cxf.server.IProductService;
import com.dynastqin.cxf.server.pojo.Product;
import com.dynastqin.cxf.util.JacksonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tantao on 14-9-4.
 */
@Component
public class ProductService implements IProductService {

    private static Map<Integer,Product> mockProducts;

    static {
        mockProducts = Maps.newHashMap();
        mockProducts.put(1,new Product(1, "iphone6", "苹果"));
        mockProducts.put(2,new Product(2, "windows7", "微软"));
    }

    @Override
    public List<Product> retrieveAllProducts() {
        List<Product> products=Lists.newArrayList();
        for(Integer key:mockProducts.keySet()){
            products.add(mockProducts.get(key));
        }
        return products;
    }

    @Override
    public Product retrieveProductById(Integer id) {
        return mockProducts.get(id);
    }

    @Override
    public List<Product> retrieveProductsByName(String name) {
        List<Product> products=Lists.newArrayList();
        for(Integer key:mockProducts.keySet()){
            Product p=mockProducts.get(key);
            if(p.getName().contains(name)){
                products.add(p);
            }
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        mockProducts.put(product.getId(),product);
        return true;
    }

    @Override
    public boolean updateProductById(Integer id, Product product) {

        Product toUpdate=mockProducts.get(id);
        if(null!=toUpdate){
            toUpdate.setName(product.getName());
            toUpdate.setCompany(product.getCompany());
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteProductById(Integer id) {
        mockProducts.remove(id);
        return true;
    }
}
