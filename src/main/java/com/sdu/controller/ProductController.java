package com.sdu.controller;

import com.sdu.bean.Product;
import com.sdu.interceptor.Auth;
import com.sdu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by J on 2017/2/25.
 */
@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning rpoducts:");
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productEditform";
    }

    @RequestMapping("product/delete/{id}")
    public  String delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "redirect:/products" ;

    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productNewform";
    }

    @RequestMapping(value = "product/edit", method = RequestMethod.POST)
    public String saveEditedProduct(Product product){

        productService.deleteProductCache(product.getId());
        productService.saveProduct(product);
        return "redirect:/product/"+ product.getId();
    }

    @RequestMapping(value = "product/new", method = RequestMethod.POST)
    public String saveProduct(Product product){

        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }


}
