package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 */

@Controller
public class BuyNowController {
    @Autowired
    private ApplicationContext context;
    private PartService partService;
    private List<Part> theParts;
    private static Product product1;
    private Product product;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2 = productService.findById(theId);

        if (product2 != null && product2.getInv() > 0) {
            product2.setInv(product2.getInv() - 1);
            productRepository.save(product2);
            return "confirmationpurchase";
        } else {
            return "errorpurchase";
        }
    }

    @PostMapping("/buyProduct")
    public String buyNow(@RequestParam("productID") int theId) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2 = productService.findById(theId);
            if (product2 != null && product2.getInv() > 0) {
                productService.decrement(product2);
                productRepository.save(product2);
                return "confirmationpurchase";
            } else {
                return "errorpurchase";
            }
        }

    }