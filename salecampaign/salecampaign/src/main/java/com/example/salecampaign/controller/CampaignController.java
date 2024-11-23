package com.example.salecampaign.controller;

import com.example.salecampaign.dto.CampaignDTO;
import com.example.salecampaign.model.Campaign;
import com.example.salecampaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("campaign")
public class CampaignController {
    @Autowired
    CampaignService campaignService;

    @PostMapping("/save")
    public List<CampaignDTO> addCampaign(@RequestBody List<Campaign> list){
        return campaignService.saveAllCampaign(list);
    }
}
