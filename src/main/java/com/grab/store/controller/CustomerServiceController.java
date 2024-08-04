package com.grab.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grab.store.model.Customer;
import com.grab.store.model.Cart;
import com.grab.store.service.CustomerService;

@RestController
@RequestMapping("/home")
public class CustomerServiceController {

	@Autowired
	private CustomerService customerService;

	////////////////////////////////////////////////////////// Admin routes

	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return new ResponseEntity<>(allCustomers, HttpStatus.OK);
	}

	@GetMapping("/admin/customers/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int customerId) {
		try {
			Optional<Customer> customer = customerService.getCustomerById(customerId);
			if (customer.isPresent()) {
				return new ResponseEntity<>(customer.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while fetching the customer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//   @PostMapping("/admin/customers")
	//  public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
	//      try {
	//          Customer customerAdded = customerService.addCustomer(customer);
	//          return new ResponseEntity<>(customerAdded, HttpStatus.CREATED);
	//      } catch (Exception e) {
	//           return new ResponseEntity<>("Customer already exists or invalid data", HttpStatus.BAD_REQUEST);
	//    }
	//   }

	@PostMapping("/admin/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			// Create and assign a new cart if not provided
			if (customer.getCart() == null) {
				Cart newCart = new Cart();
				newCart.setCustomer(customer);
				customer.setCart(newCart);
			} else {
				customer.getCart().setCustomer(customer);
			}

			Customer customerAdded = customerService.addCustomer(customer);
			return new ResponseEntity<>(customerAdded, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Customer already exists or invalid data", HttpStatus.BAD_REQUEST);
		}
	}



	@PutMapping("/admin/customers/{customerId}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable int customerId) {
		try {
			Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Customer not found or invalid data", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/admin/customers/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
		try {
			customerService.deleteCustomer(customerId);
			return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
		}
	}

	//    @GetMapping("admin/carts/{cartId}")
	//    public ResponseEntity<?> getCartById(@PathVariable Integer cartId) {
	//        try {
	//            Cart cart = customerService.getCartById(cartId);
	//            return new ResponseEntity<>(cart, HttpStatus.OK);
	//        } catch (Exception e) {
	//            return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
	//        }
	//    }

	//////////////////////////////////////////// Customer routes

	@GetMapping("/customer/customers/{customerId}")
	public ResponseEntity<?> getCustomerByIdCustomer(@PathVariable int customerId) {
		try {
			Optional<Customer> customer = customerService.getCustomerById(customerId);
			if (customer.isPresent()) {
				return new ResponseEntity<>(customer.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while fetching the customer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/customers/{customerId}")
	public ResponseEntity<?> updateCustomerCustomer(@RequestBody Customer customer, @PathVariable int customerId) {
		try {
			Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Customer not found or invalid data", HttpStatus.NOT_FOUND);
		}
	}

	//    @PostMapping("/customer/carts")
	//    public ResponseEntity<?> addCart(@RequestBody Cart cart) {
	//        try {
	//            Cart cartAdded = customerService.addCart(cart);
	//            return new ResponseEntity<>(cartAdded, HttpStatus.CREATED);
	//        } catch (Exception e) {
	//            return new ResponseEntity<>("Cart already exists or invalid data", HttpStatus.BAD_REQUEST);
	//        }
	//    }

	@PostMapping("/customer/carts")
	public ResponseEntity<?> addCart(@RequestBody Cart cart) {
		try {
			Cart cartAdded = customerService.saveCart(cart);
			return new ResponseEntity<>(cartAdded, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Cart already exists or invalid data", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/customer/carts/{cartId}/items/{itemId}")
	public ResponseEntity<?> addItemToCart(@PathVariable Integer cartId, @PathVariable Integer itemId) {
		try {
			Cart updatedCart = customerService.addItemByIdForCart(cartId, itemId);
			return new ResponseEntity<>(updatedCart, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Item not found or invalid data", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/customer/carts/{cartId}")
	public ResponseEntity<?> clearCart(@PathVariable Integer cartId) {
		try {
			Cart clearedCart = customerService.clearCart(cartId);
			return new ResponseEntity<>(clearedCart, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cart not found or invalid data", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/customer/carts/{cartId}")
	public ResponseEntity<?> viewCart(@PathVariable Integer cartId) {
		try {
			Cart cart = customerService.viewCart(cartId);
			return new ResponseEntity<>(cart, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
		}
	}


}
