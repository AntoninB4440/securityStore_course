package ab.diginamic.securityStore.controller;

import ab.diginamic.securityStore.model.Product;
import ab.diginamic.securityStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("products" , storeService.getAllProducts());
        return "products"; //templates/products.html
    }

    @PostMapping("/products")
    public String create(Model model, Product product){
        storeService.addProduct(product);
        model.addAttribute("products", storeService.getAllProducts());
        return "products";
    }
}
