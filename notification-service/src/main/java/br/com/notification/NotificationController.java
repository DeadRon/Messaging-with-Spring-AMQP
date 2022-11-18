package br.com.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private CashbackGetOrderByIdFeing orderFindOrderByIdFeing;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id){
        Order order = orderFindOrderByIdFeing.getOrderBayId(id).getBody();
        return ResponseEntity.of(Optional.of(order));
    }

}