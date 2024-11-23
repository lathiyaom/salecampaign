package com.example.salecampaign.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "campaign_discount")
public class Campaign_discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int discount;


    @ManyToOne
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Campaign campaign;


}
