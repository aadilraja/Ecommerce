package application.server.persistence.model;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private int id;


    @Column(name = "prod_name")
    private String name;

    @Column(name = "prod_description")
    private String description;

    @Column(name = "prod_brand")
    private String brand;

    @Column(name = "prod_price")
    private BigDecimal price;

    @Column(name = "prod_category")
    private String category;

    @Column(name = "prod_quantity")
    private String stockQuantity;

    @Column(name = "prod_available")
    private boolean productAvailable;

    @Column(name = "prod_release_date")
    private Date releaseDate;

    @Column(name ="image_name" )
    private String imageName;
    @Column(name ="image_type" )
    private String imageType;

    @Column(name ="image_data" )
    private byte[] imageData;

    // No-arg constructor
    public Product() {
    }

    public Product(String brand, String category, String description,
                   int id, byte[] imageData, String imageName,
                   String imageType, String name, BigDecimal price,
                   boolean productAvailable, Date releaseDate, String stockQuantity) {
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.id = id;
        this.imageData = imageData;
        this.imageName = imageName;
        this.imageType = imageType;
        this.name = name;
        this.price = price;
        this.productAvailable = productAvailable;
        this.releaseDate = releaseDate;
        this.stockQuantity = stockQuantity;
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
