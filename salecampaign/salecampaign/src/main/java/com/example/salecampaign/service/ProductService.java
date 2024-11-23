package com.example.salecampaign.service;

import com.example.salecampaign.model.PageDTO;
import com.example.salecampaign.model.Product;
import com.example.salecampaign.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> saveData(List<Product> products){
        return productRepository.saveAll(products);
    }

    public PageDTO getAllWithPagination(int page,int pageSize){
        Pageable pageable= PageRequest.of(page,pageSize);
        Page<Product> productPages = productRepository.findAll(pageable);
        List<Product> products=productPages.getContent();
        PageDTO pageDTO=new PageDTO();
        pageDTO.setProducts(productRepository.findAll());
        pageDTO.setPage(page);
        pageDTO.setPage_size(pageSize);
        pageDTO.setTotalPages(productPages.getTotalPages());

        return pageDTO;
    }
}
