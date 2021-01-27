package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        ProductServiceImpl impl = new ProductServiceImpl();
        impl.setProductRepository(productRepository);
        productService = impl;
        productRepository.deleteAllInBatch();
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Product name");
        expectedProduct.setCategory(expectedCategory);
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(12345));

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductRepr> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getName(), opt.get().getName());
        assertEquals(expectedProduct.getBrand().getName(), opt.get().getBrandName());
        assertEquals(expectedProduct.getCategory().getName(), opt.get().getCategoryName());
        assertEquals(expectedProduct.getPrice(), opt.get().getPrice());
        assertEquals(expectedProduct.getPictures(), opt.get().getPictureIds());
    }

   //todo
    // дальше пока не работает...
//    в этом основной вопрос!
//    в чем ошибка ?
//    кажется все логично - productService.findAll() отдает List<ProductRepr>,
//    но он пытается кастить с Product


    @Test
    public void testFindAll () {
        List<ProductRepr> expectedProducts = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            expectedProducts.add(createOneProduct("Category name" + i, "Brand name" + i,
                    1l + i, "Product name " + i, "12345"));
        }
        when(productService.findAll())
                .thenReturn(expectedProducts);

        List<ProductRepr> lists = productService.findAll();
        assertNotNull(productService.findAll());
//        assertNotNull(lists);
//        assertEquals( 6 , lists.size());
//        for (int i = 0; i < 6; i++) {
//            assertEquals(expectedProducts.get(i).getId(), lists.get(i).getId());
//            assertEquals(expectedProducts.get(i).getName(), lists.get(i).getName());
//            assertEquals(expectedProducts.get(i).getBrandName(), lists.get(i).getBrandName());
//        }
    }

    private ProductRepr createOneProduct (String category, String brand, Long id, String name, String price) {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(id);
        expectedProduct.setName(name);
        expectedProduct.setCategoryName(category);
        expectedProduct.setBrandName(brand);
        expectedProduct.setPictureIds(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(price));
        return expectedProduct;
    }

    //    @Test
//    public void testFindAllAndSplitProductsBy() {
//        List<List<ProductRepr>> expectedProducts = new ArrayList<>();
//        List<ProductRepr> internalProductList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            internalProductList.add(createOneProduct("Category name " + i, "Brand name " + i,
//                        1l + i, "Product name " + i, "12345"));
//            if (internalProductList.size() == 3) {
//                expectedProducts.add(internalProductList);
//                internalProductList = new ArrayList<>();
//            }
//        }
//
//        when(productService.findAllAndSplitProductsBy(3))
//                    .thenReturn(expectedProducts);
//
//
//        List<List<ProductRepr>> lists = productService.findAllAndSplitProductsBy(3);
//        assertNotNull(lists);
//        assertEquals( 2 , lists.size());
//        assertNotNull(lists.get(0));
//        assertNotNull(lists.get(1));
//        assertEquals(3, lists.get(0));
//        assertEquals(3, lists.get(1));
//        for (int i = 0; i < 2; i++) {
//            for (int ii = 0; ii < 3; ii++) {
//                assertEquals(expectedProducts.get(i).get(ii).getId(), lists.get(i).get(ii).getId());
//                assertEquals(expectedProducts.get(i).get(ii).getName(), lists.get(i).get(ii).getName());
//            }
//        }
//    }
}
