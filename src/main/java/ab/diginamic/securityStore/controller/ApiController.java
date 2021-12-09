package ab.diginamic.securityStore.controller;

import ab.diginamic.securityStore.model.Product;
import ab.diginamic.securityStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {
    @Autowired
    StoreService storeService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        System.out.println("___________ GET api/products");
        return storeService.getAllProducts();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        System.out.println("___________ POST api/products");
        Product savedProduct = storeService.addProduct(product);
        return savedProduct;
    }
}
