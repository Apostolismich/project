package gr.aueb.cf.project.controller;

import gr.aueb.cf.project.common.ApiResponse;
import gr.aueb.cf.project.dto.ProductDto;
import gr.aueb.cf.project.model.Category;
import gr.aueb.cf.project.model.Product;
import gr.aueb.cf.project.repository.CategoryRepo;
import gr.aueb.cf.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct (@RequestBody ProductDto productDto){
    Optional<Category> optionalCategory =categoryRepo.findById(productDto.getCategoryId());
    if (optionalCategory.isPresent()) {
        return new ResponseEntity<>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
    }
    productService.createProduct(productDto, optionalCategory.get());
    return new ResponseEntity<>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
       List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // create an api for edit the product
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct ( @PathVariable("productId") Integer productId ,@RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory =categoryRepo.findById(productDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

}
