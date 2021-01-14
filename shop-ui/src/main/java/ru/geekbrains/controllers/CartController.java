package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.model.LineItem;

@Controller
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String updateCart(LineItem lineItem) {
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        cartService.removeProduct(productService.findById(id).get());
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/add/product/{id}/{qty}")
    public String addProductQty(@PathVariable Long id,
                                @PathVariable int qty) {
        cartService.addProductQty(productService.findById(id).get(), qty);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/remove/product/{id}/{qty}")
    public String removeProductQty(@PathVariable Long id,
                                @PathVariable int qty) {
        cartService.removeProductQty(productService.findById(id).get(), qty);
        return "redirect:/cart";
    }



}
