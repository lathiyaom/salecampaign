package com.example.salecampaign.repository;

import com.example.salecampaign.model.Pricing_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<Pricing_history,Integer> {
}
