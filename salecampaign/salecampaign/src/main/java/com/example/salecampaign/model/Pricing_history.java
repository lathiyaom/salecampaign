package com.example.salecampaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Table(name = "pricing_history")
@Data
@Entity
public class Pricing_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private int beforeDiscountPrice;
    private int afterDiscountPrice;

    @ManyToOne
    private Product product;

}
