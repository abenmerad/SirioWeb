package com.sirioitalia.api.service;

import com.sirioitalia.api.model.Billing;
import com.sirioitalia.api.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BillingService {
    private final BillingRepository billingRepository;

    @Autowired
    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }


    public List<Billing> getBillings() {
        return billingRepository.findAll();
    }

    public void addNewBilling(Billing billing) {
        Optional<Billing> billingOptional =  billingRepository
                .findBillingByOrderNumber(billing.getOrderNumber());
        if(billingOptional.isPresent()) {
            throw new IllegalStateException("billing already exists");
        }
        billingRepository.save(billing);
    }

    public void deleteBilling(Integer billingId) {
        boolean exists = billingRepository.existsById(billingId);
        if (!exists) {
            throw new IllegalStateException("billing with id" + billingId + "does not exists");
        }
        billingRepository.deleteById(billingId);
    }

//    @Transactional
//    public void updateBilling(Integer billingId, Long orderNumber) {
//        Billing billing = billingRepository.findById(billingId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "billing with id" + billingId + "does not exists"));
//
//        if (orderNumber != null &&
//                orderNumber.length() > 0 &&
//                !Objects.equals(billing.getOrderNumber(), orderNumber)) {
//            billing.setOrderNumber(orderNumber);
//        }
//    }
}
