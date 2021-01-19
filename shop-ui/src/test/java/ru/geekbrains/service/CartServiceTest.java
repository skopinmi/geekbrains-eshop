package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOnProduct() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct,  1);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals(1, lineItem.getQty());

        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
    }

    @Test
    public void testRemoveProductQty() {
        ProductRepr expectedProduct = createProductRepr(1l, "123", "Product name");
        cartService.addProductQty(expectedProduct,  10);

        cartService.removeProductQty(expectedProduct,  5);
        List<LineItem> lineItems = cartService.getLineItems();
        LineItem lineItem = lineItems.get(0);
        assertNotNull(lineItems);
        assertEquals(5, lineItem.getQty());
        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
    }

    @Test
    public void testRemoveProductQtyIfZero() {
        ProductRepr expectedProduct = createProductRepr(1l, "123", "Product name");
        cartService.addProductQty(expectedProduct,  5);

        cartService.removeProductQty(expectedProduct,  5);
        List<LineItem> lineItems = cartService.getLineItems();
        assertEquals(0, lineItems.size());
    }

    @Test
    public void testRemoveProduct() {
        ProductRepr expectedProduct = createProductRepr(1l, "100", "Product name");
        cartService.addProductQty(expectedProduct,  5);
        cartService.removeProduct(expectedProduct);

        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(0, lineItems.size());
    }


    @Test
    public void testGetSubTotal() {
        ProductRepr expectedProduct = createProductRepr(1l, "123", "Product name1");
        ProductRepr expectedProduct1 = createProductRepr(2l, "321", "Product name2");
        cartService.addProductQty(expectedProduct,  1);
        cartService.addProductQty(expectedProduct1,  1);

        BigDecimal price = cartService.getSubTotal();
        assertNotNull(price);
        assertEquals(new BigDecimal("444"), price);
    }



    public ProductRepr createProductRepr (Long id, String price, String name) {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(id);
        expectedProduct.setPrice(new BigDecimal(price));
        expectedProduct.setName(name);
        return expectedProduct;
    }
}
