package br.com.order.service.controller;

import br.com.order.service.domain.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDTO {

    private Long id;

    private BigDecimal value = BigDecimal.ZERO;

    private Boolean paid = Boolean.FALSE;

    public OrderDTO() {

    }

    public OrderDTO(Long id, BigDecimal value, Boolean paid) {
        this.id = id;
        this.value = value;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void markAsPaid() {
        this.paid = true;
    }

    public Order toDomain(){
        return new Order(id, value, paid);
    }

    public static OrderDTO fromDomain(Order order){
        return new OrderDTO(order.getId(), order.getValue(), order.isPaid());
    }
}