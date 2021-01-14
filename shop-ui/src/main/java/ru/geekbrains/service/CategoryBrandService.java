package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;

import java.util.List;

@Service
public class CategoryBrandService {

    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;

    @Autowired

    public CategoryBrandService(CategoryRepository categoryRepository,
                                BrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Brand> findAllBrand() {
        return brandRepository.findAll();
    }
}
