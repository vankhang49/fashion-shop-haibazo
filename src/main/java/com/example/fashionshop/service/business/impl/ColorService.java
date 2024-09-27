package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.Color;
import com.example.fashionshop.repository.ColorRepository;
import com.example.fashionshop.service.business.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void remove(Long id) {
        colorRepository.deleteById(id);
    }
}
