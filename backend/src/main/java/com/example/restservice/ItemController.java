package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PutMapping("/")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return okOrFail(() -> itemService.createItem(item));
    }

    @GetMapping("/")
    @ResponseStatus( HttpStatus.OK )
    public List<Item> readItems() {
        List<Item> list = itemService.readItems();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> readItem(@PathVariable("id") int id) {
        return okOrFail(() -> itemService.readItem(id));
    }

    @PostMapping("/update")
//    @ResponseStatus( HttpStatus.ACCEPTED )
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        return okOrFail(() -> itemService.updateItem(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") int id) {
        return okOrFail(() -> {
            itemService.deleteItem(id);
            return null;
        });
    }

    private static <T> ResponseEntity<T> okOrFail(Supplier<T> supplier) {
        try {
            return new ResponseEntity<>(supplier.get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
