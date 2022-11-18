package br.com.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "orderFindOrderById",
        url = "localhost:8081/cashback"
)
public interface CashbackGetOrderByIdFeing {

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderBayId(@PathVariable Long id);

}
