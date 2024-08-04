package com.grab.store.service;

import com.grab.store.model.Store;
import com.grab.store.model.Item;
import com.grab.store.model.Category;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    List<Store> getAllStores();

    Optional<Store> getStoreById(Integer id);

    Store addStore(Store store);

    Store updateStore(Integer id, Store storeDetails);

    void deleteStore(Integer id);

    Item addItem(Item item);

    List<Item> getAllItems();

    Optional<Item> getItemById(Integer id);

    Category addCategory(Category category);
    
    Item addItemById(Integer storeId, Item item) throws Exception;
   
}
