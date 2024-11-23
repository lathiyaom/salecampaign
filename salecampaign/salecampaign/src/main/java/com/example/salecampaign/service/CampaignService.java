package com.example.salecampaign.service;

import com.example.salecampaign.dto.CampaignDTO;
import com.example.salecampaign.dto.DiscountDTO;
import com.example.salecampaign.model.Campaign;
import com.example.salecampaign.model.Campaign_discount;
import com.example.salecampaign.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {
    @Autowired
    CampaignRepository campaignRepository;

    public List<CampaignDTO> saveAllCampaign(List<Campaign> campaigns){
        for(Campaign campaign:campaigns){
            for(Campaign_discount discount:campaign.getDiscounts()){
                discount.setCampaign(campaign);
            }
        }
        List<Campaign> campaignList=campaignRepository.saveAll(campaigns);
        List<CampaignDTO> list=new ArrayList<>();

        for(Campaign campaign:campaignList){
            CampaignDTO campaignDTO=new CampaignDTO();
            campaignDTO.setId(campaign.getId());
            campaignDTO.setTitle(campaign.getTitle());
            campaignDTO.setStart_date(campaign.getStartDate());
            campaignDTO.setEnd_date(campaign.getEndDate());

            List<DiscountDTO> discountDTOS=new ArrayList<>();

            for(Campaign_discount discount:campaign.getDiscounts()){
                DiscountDTO discountDTO=new DiscountDTO();
                discountDTO.setDiscount(discount.getDiscount());
                discountDTO.setProduct_id(discount.getProduct().getProductId());

                discountDTOS.add(discountDTO);
            }

            campaignDTO.setDiscounts(discountDTOS);
            list.add(campaignDTO);
        }
        return list;
    }
}
