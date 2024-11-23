package com.example.salecampaign.model;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO {
    private List<Product> products;
    private int page;
    private int page_size;
    private int totalPages;
}
