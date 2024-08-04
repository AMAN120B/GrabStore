package com.grab.store.service;

import com.grab.store.model.Customer;
import com.grab.store.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Integer id);

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Integer id, Customer customerDetails);

    void deleteCustomer(Integer id);

//    Cart addCart(Cart cart);
//    
//    Cart getCartById(Integer cartId) throws Exception;
    
    
    Cart saveCart(Cart cart);
	
	Cart addItemByIdForCart(Integer cartId, Integer itemId);
	
	Cart clearCart(Integer cartId);
	
	Cart viewCart(Integer cartId);
}

