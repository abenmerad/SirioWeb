package com.sirioitalia.api.controller;

import com.sirioitalia.api.model.Billing;

import com.sirioitalia.api.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/billings")
public class BillingController {
    private final BillingService billingService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public List<Billing> getBillings() {
        return billingService.getBillings();
    }

    @GetMapping(path = "{billingId}")
    public Optional<Billing> getSingleBilling(@PathVariable("billingId") Integer billingId) {
        return billingService.getSingleBilling(billingId);
    }

    @PostMapping
    public void registerNewBilling(@RequestBody Billing billing) {
        billingService.addNewBilling(billing);
    }

    @DeleteMapping(path = "{billingId}")
    public void deleteBilling(@PathVariable("billingId") Integer billingId){
        billingService.deleteBilling(billingId);
    }

//    @PutMapping(path = "{billingId}")
//    public void updateBilling(
//            @PathVariable("billingId") Integer billingId,
//            @RequestParam(required = false) Long orderNumber) {
//        billingService.updateBilling(billingId, orderNumber);
//    }

}
