package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.Style;
import com.example.fashionshop.repository.StyleRepository;
import com.example.fashionshop.service.business.IStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleService implements IStyleService {

    @Autowired
    private StyleRepository styleRepository;

    @Override
    public List<Style> findAll() {
        return styleRepository.findAll();
    }

    @Override
    public Style findById(Long id) {
        return styleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Style style) {
        styleRepository.save(style);
    }

    @Override
    public void remove(Long id) {
        styleRepository.deleteById(id);
    }
}
