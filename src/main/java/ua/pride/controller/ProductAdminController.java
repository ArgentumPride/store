package ua.pride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.pride.model.Product;
import ua.pride.sevice.ProductService;

import java.util.List;

@Controller
public class ProductAdminController {
    @Autowired
    ProductService productService;

    List<Product> list;

    @GetMapping(value = "/admin")
    public ModelAndView getList() {
        ModelAndView mav = new ModelAndView("view/admin");
        list = productService.allProducts();
        mav.addObject("productList", list);
        return mav;
    }

    @GetMapping(value = "/getProduct/{id}")
    public ModelAndView getProduct(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("view/product_form");
        Product product = productService.findProductById(id);
        list = productService.allProducts();
        mav.addObject("productList", list);
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("view/product_form");
        Product product = new Product();
        mav.addObject("productForm", product);
        return mav;
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("view/product_form");
        Product product = productService.findProductById(id);
        mav.addObject("productForm", product);
        return mav;
    }

    @PostMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("productForm") Product product) {
        if(product != null && product.getId() != null) {
            productService.update(product);
        } else {
            productService.save(product);
        }
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ModelAndView("redirect:/admin");
    }
}
