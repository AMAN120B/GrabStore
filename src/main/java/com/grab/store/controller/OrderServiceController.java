package com.grab.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grab.store.model.OrderDetails;
import com.grab.store.model.Bill;
import com.grab.store.service.OrderService;

@RestController
@RequestMapping("/home")
public class OrderServiceController {

    @Autowired
    private OrderService orderService;

    // Admin routes

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderDetails>> getAllOrders() {
        List<OrderDetails> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/admin/orders/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable int orderId) {
        try {
            Optional<OrderDetails> order = orderService.getOrderById(orderId);
            if (order.isPresent()) {
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/orders")
    public ResponseEntity<?> addOrder(@RequestBody OrderDetails orderDetails) {
        try {
            OrderDetails orderAdded = orderService.addOrder(orderDetails);
            return new ResponseEntity<>(orderAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Order already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }
//    public ResponseEntity<?> addOrder(@RequestBody OrderDetails orderDetails) {
//        try {
//            OrderDetails orderAdded = orderService.addOrder(orderDetails);
//            return new ResponseEntity<>(orderAdded, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Order already exists or invalid data", HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping("/admin/orders/{orderId}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDetails orderDetails, @PathVariable int orderId) {
        try {
            OrderDetails updatedOrder = orderService.updateOrder(orderId, orderDetails);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Order not found or invalid data", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/bills")
    public ResponseEntity<?> addBill(@RequestBody Bill bill) {
        try {
            Bill billAdded = orderService.addBill(bill);
            return new ResponseEntity<>(billAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Bill already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/bills")
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> allBills = orderService.getAllBills();
        return new ResponseEntity<>(allBills, HttpStatus.OK);
    }

    @GetMapping("/admin/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable int billId) {
        try {
            Optional<Bill> bill = orderService.getBillById(billId);
            if (bill.isPresent()) {
                return new ResponseEntity<>(bill.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the bill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Customer routes

    @GetMapping("/customer/orders/{orderId}")
    public ResponseEntity<?> getOrderByIdCustomer(@PathVariable int orderId) {
        try {
            Optional<OrderDetails> order = orderService.getOrderById(orderId);
            if (order.isPresent()) {
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/bills/{billId}")
    public ResponseEntity<?> getBillByIdCustomer(@PathVariable int billId) {
        try {
            Optional<Bill> bill = orderService.getBillById(billId);
            if (bill.isPresent()) {
                return new ResponseEntity<>(bill.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the bill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Store routes

    @GetMapping("/store/orders")
    public ResponseEntity<List<OrderDetails>> getAllOrdersStore() {
        List<OrderDetails> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/store/orders/{orderId}")
    public ResponseEntity<?> getOrderByIdStore(@PathVariable int orderId) {
        try {
            Optional<OrderDetails> order = orderService.getOrderById(orderId);
            if (order.isPresent()) {
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/store/orders/{orderId}")
    public ResponseEntity<?> updateOrderStore(@RequestBody OrderDetails orderDetails, @PathVariable int orderId) {
        try {
            OrderDetails updatedOrder = orderService.updateOrder(orderId, orderDetails);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Order not found or invalid data", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/store/orders/{orderId}")
    public ResponseEntity<?> deleteOrderStore(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/store/bills")
    public ResponseEntity<?> addBillStore(@RequestBody Bill bill) {
        try {
            Bill billAdded = orderService.addBill(bill);
            return new ResponseEntity<>(billAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Bill already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/store/bills")
    public ResponseEntity<List<Bill>> getAllBillsStore() {
        List<Bill> allBills = orderService.getAllBills();
        return new ResponseEntity<>(allBills, HttpStatus.OK);
    }

    @GetMapping("/store/bills/{billId}")
    public ResponseEntity<?> getBillByIdStore(@PathVariable int billId) {
        try {
            Optional<Bill> bill = orderService.getBillById(billId);
            if (bill.isPresent()) {
                return new ResponseEntity<>(bill.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the bill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
