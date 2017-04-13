package com.sdu.service;

import com.sdu.bean.Product;



/**
 * Created by J on 2017/2/25.
 */
public interface ProductService {

    Iterable<Product> listAllProducts();

    Product getProductById(Integer Id);

    Product saveProduct(Product product);

    void deleteProduct(Integer Id);

    void deleteProductCache(Integer Id);
}
