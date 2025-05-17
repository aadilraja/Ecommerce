package application.server.persistence.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private String stockQuantity;
    private boolean productAvailable;
    private Date releaseDate;
    private String imageName;
    private String imageType;
    private byte[] imageData;

    public ProductDTO(String brand, String category, String description,
                      int id, byte[] imageData, String imageName, String imageType, String name,
                      BigDecimal price, boolean productAvailable, Date releaseDate, String stockQuantity)
    {
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
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


}
