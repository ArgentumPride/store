package ua.pride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.pride.model.Product;
import ua.pride.sevice.ProductService;

import java.util.List;

@Controller(value = "/list")
public class ProductController {
    @Autowired
    ProductService productService;

    List<Product> list;

    @GetMapping(value = "/list/products")
    public ModelAndView getList() {
        ModelAndView mav = new ModelAndView("view/list");
        list = productService.allProducts();
        mav.addObject("productList", list);
        return mav;
    }

    @GetMapping(value = "/list/ascByPrice")
    public ModelAndView ascByPrice() {
        ModelAndView mav = new ModelAndView("view/list");
        list = productService.ascByPrice();
        mav.addObject("productList", list);
        return mav;
    }

    @GetMapping(value = "/list/descByPrice")
    public ModelAndView descByPrice() {
        ModelAndView mav = new ModelAndView("view/list");
        list = productService.descByPrice();
        mav.addObject("productList", list);
        return mav;
    }
}
