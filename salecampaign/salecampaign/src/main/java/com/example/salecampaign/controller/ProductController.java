package com.example.salecampaign.controller;

import com.example.salecampaign.model.PageDTO;
import com.example.salecampaign.model.Product;
import com.example.salecampaign.service.ProductService;
import com.example.salecampaign.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("storeData")
    public List<Product> storeData(@RequestBody List<Product> products){
        return productService.saveData(products);
    }

    @GetMapping("products1")
    public PageDTO getProductsWithPagination(@RequestParam (defaultValue = AppConstants.page)int page, @RequestParam (defaultValue = AppConstants.pageSize)int pageSize){
        return productService.getAllWithPagination(page,pageSize);
    }

}
