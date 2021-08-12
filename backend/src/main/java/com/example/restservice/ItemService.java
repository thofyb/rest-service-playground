package com.example.restservice;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final List<Item> itemList;
    private final AtomicInteger idCounter;

    public ItemService() {
        itemList = new ArrayList<>(20);
        idCounter = new AtomicInteger(0);

        createItem(new Item(0, "Item1", 100.0));
        createItem(new Item(0, "Item2", 200.0));
        createItem(new Item(0, "Item3", 300.0));
    }

    @NonNull
    public Item createItem(@NonNull Item item) {
        item.setId(idCounter.getAndIncrement());
        itemList.add(item);
        return item;
    }

    @NonNull
    public List<Item> readItems() {
        return itemList.stream()
                .map(item -> item == null ? null : new Item(item))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @NonNull
    public Item readItem(int itemId) {
        Item item = itemList.get(itemId);
        return item;
    }

    @NonNull
    public Item updateItem(@NonNull Item item) {
        Item updatedItem = itemList.get(item.getId());
        updatedItem.setDesc(item.getDesc());
        updatedItem.setPrice(item.getPrice());
        return updatedItem;
    }

    public void deleteItem(int itemId) {
        itemList.set(itemId, null);
    }
}
