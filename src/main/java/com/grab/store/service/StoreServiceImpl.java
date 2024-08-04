package com.grab.store.service;

import com.grab.store.model.Store;
import com.grab.store.model.Category;
import com.grab.store.model.Item;
import com.grab.store.repo.CategoryDAO;
import com.grab.store.repo.ItemDAO;
import com.grab.store.repo.StoreDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDAO storeRepository;

    @Autowired
    private ItemDAO itemRepository;

    @Autowired
    private CategoryDAO categoryRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> getStoreById(Integer id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Integer id, Store storeDetails) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found"));
        store.setStoreName(storeDetails.getStoreName());
        store.setStoreManager(storeDetails.getStoreManager());
        store.setStorePhone(storeDetails.getStorePhone());
        store.setAddress(storeDetails.getAddress());
        store.setItemList(storeDetails.getItemList());
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
    
    @Override
    public Item addItemById(Integer storeId, Item item) throws Exception {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception("Store not found"));
        store.getItemList().add(item);
        itemRepository.save(item); // Save the item separately if you need to
        storeRepository.save(store); // Save the store to update its item list
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemById(Integer id) {
        return itemRepository.findById(id);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
