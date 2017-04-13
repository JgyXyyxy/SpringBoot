package com.sdu.repositories;

import com.sdu.bean.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by J on 2017/2/25.
 */
public interface ProductRepository extends CrudRepository<Product,Integer>{
}
