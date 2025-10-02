package com.example.board.controller;

import com.example.board.dto.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final List<Order> orderList = new ArrayList<>();

    // Sample data for demonstration
    public OrderController() {
        orderList.add(new Order(1L, "shipped", "2023-10-20", "John Doe"));
        orderList.add(new Order(2L, "processing", "2023-10-21", "Jane Smith"));
    }

    // 1. 주문 ID로 주문 조회
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable(name = "orderId") Long orderId) {
        return orderList.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(new Order()); // oprional method
    }

//    @GetMapping("/{orderId}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
//        return orderList.stream()
//                .filter(order -> order.getOrderId().equals(orderId))
//                .findFirst()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    // 2. 상태와 날짜로 주문 필터링
    @GetMapping("/filter")
    public ResponseEntity<List<Order>> getOrdersByStatusAndDate(
            @RequestParam(name = "status") String status,
            @RequestParam(name= "date", required = false) String date) {


        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getStatus().equalsIgnoreCase(status) &&
                    (date == null || order.getDate().equals(date))) {
                filteredOrders.add(order);
            }
        }
        return ResponseEntity.ok(filteredOrders);
    }

    // 3. 주문 생성
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        orderList.add(newOrder); // 간단한 예제를 위해 리스트에 추가
        return ResponseEntity.ok(newOrder);
    }
}

