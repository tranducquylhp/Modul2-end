package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductType;
import com.codegym.service.ProductService;
import com.codegym.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public Iterable<ProductType> productTypes(){
        return productTypeService.findAll();
    }

    @GetMapping("/products")
    public ModelAndView listProduct(@RequestParam("s") Optional<String> s, @PageableDefault(value = 3) Pageable pageable, HttpServletRequest request){
        Page<Product> products;
        ModelAndView modelAndView = new ModelAndView("/product/list");
        if(s.isPresent()){
            products = productService.findAllByNameContaining(s.get(), pageable);
            modelAndView.addObject("nameSearch", s.get());
        } else {
            products = productService.findAll(pageable);
        }
        modelAndView.addObject("products", products);
        if (request.getParameter("message")!= null){
            modelAndView.addObject("message",request.getParameter("message"));
        }
        List<Integer> productPage = productService.getNumberPage(products);
        modelAndView.addObject("productPages", productPage);
        return modelAndView;
    }

    @GetMapping("/product/create")
    public ModelAndView createProduct(){
        ModelAndView modelAndView = new ModelAndView("/product/create","product",new Product());
        return modelAndView;
    }

    @PostMapping("/product/create")
    public ModelAndView saveProduct(@Valid @ModelAttribute Product product, BindingResult result){
        if (result.hasFieldErrors()) {
            return new ModelAndView("/product/create");
        }
        product.setDateCreate(LocalDate.now());
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        modelAndView.addObject("message","Create successful");
        return modelAndView;
    }

    @GetMapping("/product/edit/{id}")
    public ModelAndView showEditForm(@PathVariable long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/product/edit/")
    public ModelAndView updateProduct(@Valid @ModelAttribute Product product, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        if (!result.hasFieldErrors()) {
            productService.save(product);
            modelAndView.addObject("message", "Update Successful");
        }
        return modelAndView;
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/product/delete")
    public ModelAndView deleteProduct(@ModelAttribute Product product){
        productService.delete(product.getId());
        return new ModelAndView("redirect:/products", "message","Delete Successful");
    }
}
