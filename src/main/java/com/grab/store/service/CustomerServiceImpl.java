package com.grab.store.service;

import com.grab.store.model.Customer;
import com.grab.store.model.Item;
import com.grab.store.repo.CartDAO;
import com.grab.store.repo.CustomerDAO;
import com.grab.store.repo.ItemDAO;
import com.grab.store.model.Cart;

import com.grab.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerRepository;

    @Autowired
    private CartDAO cartRepository;
    
    @Autowired 
    private ItemDAO itemRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setMobileNumber(customerDetails.getMobileNumber());
        customer.setEmail(customerDetails.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Cart saveCart(Cart cart) {
        // Save or update the cart
        return cartRepository.save(cart);
    }
    
    @Override
    public Cart addItemByIdForCart(Integer cartId, Integer itemId) {
        // Fetch the cart and item
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        
        // Add item to the cart's item list
        cart.getItemList().add(item);
        
        // Save the updated cart
        return cartRepository.save(cart);
    }
    
    @Override
    public Cart clearCart(Integer cartId) {
        // Fetch the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        // Clear all items from the cart
        cart.getItemList().clear();
        
        // Save the updated cart
        return cartRepository.save(cart);
    }
    
    @Override
    public Cart viewCart(Integer cartId) {
        // Fetch and return the cart
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    
    
}
