package br.com.cashback;

import br.com.cashback.Order;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "orderFindOrderById",
        url = "localhost:8080/v1/orders"
)
public interface OrderFindOrderByIdFeing {

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderBayId(@PathVariable Long id);

}
