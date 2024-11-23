package com.example.salecampaign.schedular;

import com.example.salecampaign.model.Campaign;
import com.example.salecampaign.model.Campaign_discount;
import com.example.salecampaign.model.Pricing_history;
import com.example.salecampaign.model.Product;
import com.example.salecampaign.repository.CampaignRepository;
import com.example.salecampaign.repository.DiscountRepository;
import com.example.salecampaign.repository.HistoryRepository;
import com.example.salecampaign.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class UserScheduler {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void CampaignStart(){
        List<Object[]> camplist=campaignRepository.getCampaignStartById();
        if(camplist.isEmpty()){
            return;
        }
        System.out.println("sale Start...");
        for(Object[] camp : camplist){
            Campaign campaign=campaignRepository.findById((int)camp[0]).get();
            List<Campaign_discount> discountList=campaign.getDiscounts();

            for(Campaign_discount  discount : discountList){
                Product product=productRepository.findById(discount.getProduct().getProductId()).get();
                int dis=discount.getDiscount();

                int oldCur=product.getCurrentPrice();
                int newPrice=((product.getMRP() * dis)/100);
                product.setCurrentPrice((oldCur-newPrice));
                product.setDiscount(product.getDiscount()+dis);
                productRepository.save(product);
                System.out.println("product saved");

                Pricing_history pricingHistory=new Pricing_history();
                pricingHistory.setDate(LocalDate.now());
                pricingHistory.setBeforeDiscountPrice(oldCur);
                pricingHistory.setAfterDiscountPrice(product.getCurrentPrice());
                pricingHistory.setProduct(product);
                historyRepository.save(pricingHistory);
                System.out.println("history saved");
            }
        }
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void CampaignStop(){
        List<Object[]> campList=campaignRepository.getCampaignStopById();
        if(campList.isEmpty()){
            return;
        }
        System.out.println("sale stop...");
        for(Object[] camp:campList){
            Campaign campaign=campaignRepository.findById((int) camp[0]).get();
            List<Campaign_discount> discountList=campaign.getDiscounts();

            for(Campaign_discount  discount : discountList){
                Product product=productRepository.findById(discount.getProduct().getProductId()).get();
                int dis=discount.getDiscount();

                int oldCur=product.getCurrentPrice();
                int newPrice=((product.getMRP() * dis)/100);
                product.setCurrentPrice((oldCur+newPrice));
                product.setDiscount(product.getDiscount()-dis);
                productRepository.save(product);
                System.out.println("product saved");

                Pricing_history pricingHistory=new Pricing_history();
                pricingHistory.setDate(LocalDate.now());
                pricingHistory.setBeforeDiscountPrice(oldCur);
                pricingHistory.setAfterDiscountPrice(product.getCurrentPrice());
                pricingHistory.setProduct(product);
                historyRepository.save(pricingHistory);
                System.out.println("history saved");
            }
        }
    }
}
