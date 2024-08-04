package com.grab.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grab.store.model.Item;
import com.grab.store.model.Store;
import com.grab.store.model.Category;
import com.grab.store.service.StoreService;

@RestController
@RequestMapping("/home")
public class StoreServiceController {

    @Autowired
    private StoreService storeService;

//    @Autowired
//    private ItemService itemService;
//    // Admin routes

    @GetMapping("/admin/stores")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> allStores = storeService.getAllStores();
        return new ResponseEntity<>(allStores, HttpStatus.OK);
    }

    @GetMapping("/admin/stores/{storeId}")
    public ResponseEntity<?> getStoreById(@PathVariable int storeId) {
        try {
            Optional<Store> store = storeService.getStoreById(storeId);
            if (store.isPresent()) {
                return new ResponseEntity<>(store.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the store", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/stores")
    public ResponseEntity<?> addStore(@RequestBody Store store) {
        try {
            Store storeAdded = storeService.addStore(store);
            return new ResponseEntity<>(storeAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Store already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/admin/stores/{storeId}")
    public ResponseEntity<?> updateStore(@RequestBody Store store, @PathVariable int storeId) {
        try {
            Store updatedStore = storeService.updateStore(storeId, store);
            return new ResponseEntity<>(updatedStore, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Store not found or invalid data", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/stores/{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable Integer storeId) {
        try {
            storeService.deleteStore(storeId);
            return new ResponseEntity<>("Store deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/items")
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        try {
            Item itemAdded = storeService.addItem(item);
            return new ResponseEntity<>(itemAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Item already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> allItems = storeService.getAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/admin/items/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable int itemId) {
        try {
            Optional<Item> item = storeService.getItemById(itemId);
            if (item.isPresent()) {
                return new ResponseEntity<>(item.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            Category categoryAdded = storeService.addCategory(category);
            return new ResponseEntity<>(categoryAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Category already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    // Customer routes

    @GetMapping("/customer/stores")
    public ResponseEntity<List<Store>> getAllStoresCustomer() {
        List<Store> allStores = storeService.getAllStores();
        return new ResponseEntity<>(allStores, HttpStatus.OK);
    }

    @GetMapping("/customer/stores/{storeId}")
    public ResponseEntity<?> getStoreByIdCustomer(@PathVariable int storeId) {
        try {
            Optional<Store> store = storeService.getStoreById(storeId);
            if (store.isPresent()) {
                return new ResponseEntity<>(store.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the store", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/items")
    public ResponseEntity<List<Item>> getAllItemsCustomer() {
        List<Item> allItems = storeService.getAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/customer/items/{itemId}")
    public ResponseEntity<?> getItemByIdCustomer(@PathVariable int itemId) {
        try {
            Optional<Item> item = storeService.getItemById(itemId);
            if (item.isPresent()) {
                return new ResponseEntity<>(item.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Store routes

    @PutMapping("/store/stores/{storeId}")
    public ResponseEntity<?> updateStoreStore(@RequestBody Store store, @PathVariable int storeId) {
        try {
            Store updatedStore = storeService.updateStore(storeId, store);
            return new ResponseEntity<>(updatedStore, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Store not found or invalid data", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/store/{storeId}/items")
    public ResponseEntity<?> addItemToStore(@PathVariable Integer storeId, @RequestBody Item item) {
        try {
            Item itemAdded = storeService.addItemById(storeId, item);
            return new ResponseEntity<>(itemAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Item already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/store/items")
    public ResponseEntity<List<Item>> getAllItemsStore() {
        List<Item> allItems = storeService.getAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/store/{storeId}/items")
    public ResponseEntity<?> getItemsByStoreId(@PathVariable Integer storeId) {
        Optional<Store> storeOpt = storeService.getStoreById(storeId);
        if (storeOpt.isPresent()) {
            List<Item> items = storeOpt.get().getItemList();
            return new ResponseEntity<>(items, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        }
    }


    
    @GetMapping("/store/items/{itemId}")
    public ResponseEntity<?> getItemByIdStore(@PathVariable int itemId) {
        try {
            Optional<Item> item = storeService.getItemById(itemId);
            if (item.isPresent()) {
                return new ResponseEntity<>(item.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/store/categories")
    public ResponseEntity<?> addCategoryStore(@RequestBody Category category) {
        try {
            Category categoryAdded = storeService.addCategory(category);
            return new ResponseEntity<>(categoryAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Category already exists or invalid data", HttpStatus.BAD_REQUEST);
        }
    }
}
