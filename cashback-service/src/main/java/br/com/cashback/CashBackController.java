package br.com.cashback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Optional.of;

@RestController
@RequestMapping(value = "/cashback")
public class CashBackController {

    @Autowired
    private OrderFindOrderByIdFeing orderFindOrderByIdFeing;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = orderFindOrderByIdFeing.getOrderBayId(id).getBody();
        return ResponseEntity.of(of(order));
    }

}
