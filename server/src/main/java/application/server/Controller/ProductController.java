package application.server.Controller;

import application.server.Service.ProductService;
import application.server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService)
    {
        this.productService = productService;

    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){

      return  new ResponseEntity<> (productService.sendAllProducts(), HttpStatus.OK);

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product=productService.getProdById(id);
        if(product!=null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {

        try {
            productService.addProduct(product, imageFile);
            return new ResponseEntity<>("Product added successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{prodId}/image")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable int prodId) {
        Product product=productService.getProdById(prodId);

        byte [] fileData=product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(fileData);


    }
    @PutMapping("/product/{prodId}")
    public ResponseEntity<String>updateProduct(@PathVariable int prodId,
                                               @RequestPart Product product,
                                               @RequestPart MultipartFile imageFile)
    {
       Product product1=null;

       try
       {
           product1=productService.updateProduct(prodId,product,imageFile);
       }catch (IOException e)
       {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }

       if(product1!=null)
       {
           return new ResponseEntity<>("Product updated successfully!", HttpStatus.OK);
       }
       else
           return new ResponseEntity<>("Product doesnt exist",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/product/{prodId}")
    public void deleteProduct(@PathVariable int prodId) {
        productService.deleteProdById(prodId);



    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>>Search(@RequestParam String keyword) {
        System.out.println(keyword);

        List<Product> productList=productService.searchProduct(keyword);
        return new ResponseEntity<>(productList, HttpStatus.OK) ;
    }






}
