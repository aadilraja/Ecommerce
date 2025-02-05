package application.server.model;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")  // Explicitly name the column
    private int id;  // Updated to match frontend's "id"

    @Column(name = "prod_name")  // Explicitly name the column
    private String name;  // Updated to match frontend's "name"

    @Column(name = "prod_description")  // Explicitly name the column
    private String description;  // Updated to match frontend's "description"

    @Column(name = "prod_brand")  // Explicitly name the column
    private String brand;  // Updated to match frontend's "brand"

    @Column(name = "prod_price")  // Explicitly name the column
    private BigDecimal price;  // Updated to match frontend's "price"

    @Column(name = "prod_category")  // Explicitly name the column
    private String category;  // Updated to match frontend's "category"

    @Column(name = "prod_quantity")  // Explicitly name the column
    private String stockQuantity;  // Updated to match frontend's "stockQuantity"

    @Column(name = "prod_available")  // Explicitly name the column
    private boolean productAvailable;  // Updated to match frontend's "productAvailable"

    @Column(name = "prod_release_date")  // Explicitly name the column
    private Date releaseDate;  // Updated to match frontend's "releaseDate"

    private String imageName;
    private String imageType;

    @Column(name ="image_data" )
    private byte[] imageData;

    // No-arg constructor
    public Product() {
    }

    // All-args constructor
    public Product(int id, String name, String description, String brand, BigDecimal price,
                   String category, String stockQuantity, boolean productAvailable, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.productAvailable = productAvailable;
        this.releaseDate = releaseDate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public boolean isProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageType() {
        return imageType;
    }

    public String getImageName() {
        return imageName;
    }
    public byte[] getImageData() {
        return imageData;
    }
}
