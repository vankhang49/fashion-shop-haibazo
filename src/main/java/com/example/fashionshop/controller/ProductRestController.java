package com.example.fashionshop.controller;

import com.example.fashionshop.dto.respone.ErrorDetail;
import com.example.fashionshop.entities.Product;
import com.example.fashionshop.service.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public/products")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(name = "pageSize", defaultValue = "12") int pageSize,
                                            @RequestParam(name = "nameSearch", defaultValue = "") String nameSearch,
                                            @RequestParam(name = "categoryName", defaultValue = "") String categoryName,
                                            @RequestParam(name = "styleName", defaultValue = "") String styleName,
                                            @RequestParam(name = "colorName", defaultValue = "") String colorName,
                                            @RequestParam(name = "size", defaultValue = "") String size,
                                            @RequestParam(name = "priceBefore", defaultValue = "0") Double priceBefore,
                                            @RequestParam(name = "priceAfter", defaultValue = "9999999999") Double priceAfter,
                                            @RequestParam(name = "nameSort", defaultValue = "") String nameSort,
                                            @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection
    ) {
        if (pageSize < 0) {
            pageSize = 12;
        }
        List<Sort.Order> orders = new ArrayList<>();
            switch (nameSort.toLowerCase()) {
                case "datecreate":
                    orders.add(createSortOrder("date_create", sortDirection));
                    break;
                case "price":
                    orders.add(createSortOrder("pr.price", sortDirection));
                    break;
                default:
                    orders.add(createSortOrder("date_create", "desc"));
            }

        System.out.println("nameSearch(" + nameSearch + ")" + "categoryName(" + categoryName + ")" + "styleName(" + styleName + ")" );
        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by(orders));
        Slice<Product> products = productService.findAllProductsByProductNameAndCategoryNameAndStyleNameAndColorAndSizeBetweenPriceBeforeAndPriceAfter(nameSearch, categoryName, styleName, colorName, size, priceBefore, priceAfter, pageRequest);
        if (products.isEmpty()) {
            return ResponseEntity.status(404).body("No products found!");
        }
        return ResponseEntity.ok(products);
    }

    /**
     * Creates a Sort.Order instance based on the given sort field and direction.
     *
     * @param sortBy       The field to sort by.
     * @param sortDirection The sort direction ('asc' or 'desc').
     * @return A Sort.Order instance representing the sort criteria.
     */
    private Sort.Order createSortOrder(String sortBy, String sortDirection) {
        if (sortDirection == null || sortDirection.isEmpty() ||
                (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc"))) {
            sortDirection = "asc";
        }
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        return new Sort.Order(direction, sortBy);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable(name = "productId") Long productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.status(404).body("Không tìm thấy thông tin sản phẩm!");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveProduct(@Validated @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorDetail errors = new ErrorDetail("Validation errors");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.addError(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        System.out.println(product);
        productService.save(product);
        return new ResponseEntity<>(200, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateProduct(@Validated @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorDetail errors = new ErrorDetail("Validation errors");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.addError(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(product);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PatchMapping("/{productId}/soft-delete")
    public ResponseEntity<?> softDeleteProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.softDeleteProduct(productId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PatchMapping("/{productId}/restore")
    public ResponseEntity<?> restoreProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.restoreProduct(productId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.remove(productId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
}
