package com.example.fashionshop.controller;

import com.example.fashionshop.entities.Color;
import com.example.fashionshop.service.business.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/colors")
public class ColorRestController {
    @Autowired
    private IColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = colorService.findAll();
        if (colors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colors);
    }
}
