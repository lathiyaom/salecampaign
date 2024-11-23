package com.example.salecampaign.model;

import com.example.salecampaign.model.Campaign_discount;
import com.example.salecampaign.model.Pricing_history;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String title;
    private String description;
    private int MRP;
    private int currentPrice;
    private int discount;
    private int inventoryCount;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Campaign_discount> campaignDiscounts=new ArrayList<>();

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pricing_history> pricingHistories=new ArrayList<>();

    @JsonCreator
    public Product(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMRP() {
        return MRP;
    }

    public void setMRP(int MRP) {
        this.MRP = MRP;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public List<Campaign_discount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<Campaign_discount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }

    public List<Pricing_history> getPricingHistories() {
        return pricingHistories;
    }

    public void setPricingHistories(List<Pricing_history> pricingHistories) {
        this.pricingHistories = pricingHistories;
    }

    public Product(){}
}