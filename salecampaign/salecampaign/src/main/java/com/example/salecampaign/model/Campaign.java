package com.example.salecampaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


@Table(name = "campaign")
@Data
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "campaign",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Campaign_discount> discounts=new ArrayList<>();

    public Campaign setStart_date(String start_date){
        this.startDate=LocalDate.parse(start_date);
        return this;
    }

    public Campaign setEnd_date(String end_date){
        LocalDate date=LocalDate.parse(end_date);
        date=date.plusDays(1);
        this.endDate=date;
        return this;
    }

    public void addDiscount(Campaign_discount discount){
        discount.setCampaign(this);
        this.discounts.add(discount);
    }

    public void removeDiscount(Campaign_discount discount){
        discount.setCampaign(null);
        this.discounts.remove(discount);
    }

}
