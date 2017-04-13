package com.sdu.service;

import com.sdu.bean.Product;
import com.sdu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by J on 2017/2/25.
 */
@Service
@CacheConfig(cacheNames = "productCache")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Cacheable(keyGenerator ="MykeyGenerator")
    public Product getProductById(Integer Id) {
        Product product = productRepository.findOne(Id);
        System.out.println("缓存："+product.getId());
        return product;
    }

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})
//    @CacheEvict(keyGenerator ="MykeyGenerator")
    public Product saveProduct(Product product) {
//        deleteProductCache(product.getId());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    @CacheEvict(keyGenerator ="MykeyGenerator")
    public void deleteProduct(Integer Id) {
        productRepository.delete(Id);
        System.out.println("删除数据并删除缓存："+Id);
    }

    @CacheEvict(keyGenerator ="MykeyGenerator")
    public void deleteProductCache(Integer Id){
        System.out.println("删除缓存："+Id);
    }
}
