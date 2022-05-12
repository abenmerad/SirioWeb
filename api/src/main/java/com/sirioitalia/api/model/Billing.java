package com.sirioitalia.api.model;

import javax.persistence.*;

@Entity
@Table
public class Billing {
    @Id
    @SequenceGenerator(
            name="billing_sequence",
            sequenceName = "billing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "billing_sequence"
    )
    private Integer id;

    private Long orderNumber;

    public Billing() {
    }

    public Billing(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Billing(Integer id, Long orderNumber) {
        this.id = id;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
