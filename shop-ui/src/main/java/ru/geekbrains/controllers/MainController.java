package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.CategoryBrandService;
import ru.geekbrains.service.ProductService;

@Controller
public class MainController {

    private final ProductService productService;
    private final CategoryBrandService categoryBrandService;

    @Autowired
    public MainController(ProductService productService, CategoryBrandService categoryBrandService) {
        this.productService = productService;
        this.categoryBrandService = categoryBrandService;
    }

    @RequestMapping({"/", "/index", "/products"})
    public String indexPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryBrandService.findAllCategory());
        model.addAttribute("brands", categoryBrandService.findAllBrand());
        return "products";
    }

    @RequestMapping("/product/{id}")
    public String productDetailPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow(IllegalArgumentException::new));
        model.addAttribute("categories", categoryBrandService.findAllCategory());
        model.addAttribute("brands", categoryBrandService.findAllBrand());
        return "product";
    }
}
