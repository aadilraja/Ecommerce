package application.server.Controller;

import application.server.Service.ProductService;
import application.server.persistence.DTOs.ProductDTO;
import application.server.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import application.server.persistence.mapper.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    ProductService productService;
    Mapper mp;

    @Autowired
    public ProductController(ProductService productService, Mapper mp) {
        this.productService = productService;
        this.mp = mp;

    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {

        List<ProductDTO> productsList=mp.toProductDTOList(productService.sendAllProducts());

        return new ResponseEntity<>(productsList, HttpStatus.OK);


    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        Product product = productService.getProdById(id);
        if (product != null) {
            ProductDTO productDTO = mp.toDTO(product); // Assuming 'mp' is a mapper bean injected in the controller
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart("product") ProductDTO productDTO,
                                        @RequestPart MultipartFile imageFile) {

        Product product = mp.toEntity(productDTO);

        try {
            productService.addProduct(product, imageFile);
            return new ResponseEntity<>("Product added successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{prodId}/image")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable int prodId) {
        Product product = productService.getProdById(prodId);

        byte[] fileData = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(fileData);


    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity<String> updateProduct(@PathVariable int prodId,
                                                @RequestPart ProductDTO productDTO,
                                                @RequestPart MultipartFile imageFile) {
        Product product1 = null;

        try {
            // Convert DTO to Entity before sending to service
            Product product = mp.toEntity(productDTO);
            product1 = productService.updateProduct(prodId, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (product1 != null) {
            return new ResponseEntity<>("Product updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/{prodId}")
    public void deleteProduct(@PathVariable int prodId) {
        productService.deleteProdById(prodId);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDTO>> search(@RequestParam String keyword) {
        System.out.println(keyword);

        List<Product> productList = productService.searchProduct(keyword);

        List<ProductDTO> productDTOList = productList.stream()
                .map(mp::toDTO)
                .toList();

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
}