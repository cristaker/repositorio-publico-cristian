package com.mok.services;

import com.mok.exceptions.CategoryNotFoundException;
import com.mok.exceptions.InvalidCategoryException;
import com.mok.models.Category;
import com.mok.models.DTO.CategoryDTO;
import com.mok.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO findById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .orElseThrow(() -> new CategoryNotFoundException("categoria no encontrada" + id));
    }

    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new InvalidCategoryException("Category name cannot be empty");
        }
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("categoria no encontrada" + id);
        }
        categoryRepository.deleteById(id);
    }
}
