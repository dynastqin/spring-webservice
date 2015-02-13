package com.dynastqin.cxf.server;

import com.dynastqin.cxf.server.pojo.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 基于JAX-RS的Restful webservice
 * Created by tantao on 14-9-4.
 */
@Produces(MediaType.APPLICATION_JSON)
public interface IProductService {

    @GET
    @Path("/products")
    List<Product> retrieveAllProducts();

    @GET
    @Path("/product/{id}")
    Product retrieveProductById(@PathParam("id") Integer id);

    @GET
    @Path("/products/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    List<Product> retrieveProductsByName(@PathParam("name") String name);

    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    boolean addProduct(Product product);

    @PUT
    @Path("/product/{id}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    boolean updateProductById(@PathParam("id") Integer id, Product product);

    @DELETE
    @Path("/product/{id}")
    boolean deleteProductById(@PathParam("id") Integer id);
}
