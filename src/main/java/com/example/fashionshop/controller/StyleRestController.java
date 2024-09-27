package com.example.fashionshop.controller;

import com.example.fashionshop.entities.Color;
import com.example.fashionshop.entities.Style;
import com.example.fashionshop.service.business.IStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/styles")
public class StyleRestController {

    @Autowired
    private IStyleService styleService;

    @GetMapping
    public ResponseEntity<List<Style>> getAllColors() {
        List<Style> styles = styleService.findAll();
        if (styles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(styles);
    }

}
