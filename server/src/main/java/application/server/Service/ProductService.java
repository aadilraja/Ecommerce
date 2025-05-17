package application.server.Service;

import application.server.persistence.Repo.ProductRepo;
import application.server.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Corrected method name and implementation
    public List<Product> sendAllProducts() {
        return productRepo.findAll();
    }

    public Product getProdById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public void addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        productRepo.save(product);



    }

    public Product updateProduct(int prodId, Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return productRepo.save(product);

    }

    public void deleteProdById(int prodId) {
         productRepo.deleteById(prodId);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepo.Search(keyword);

    }
}