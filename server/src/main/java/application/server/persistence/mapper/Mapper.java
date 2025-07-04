package application.server.persistence.mapper;

import application.server.Service.UserService;
import application.server.persistence.DTOs.ProductDTO;
import application.server.persistence.DTOs.UserCreateDTO;
import application.server.persistence.DTOs.UserInfoDTO;
import application.server.persistence.Repo.RoleRepo;
import application.server.persistence.model.Product;
import application.server.persistence.model.Role;
import application.server.persistence.model.UserInfo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    @Autowired
    public Mapper(PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    public  ProductDTO toDTO(Product product) {
        if(product == null)
            return null;
        return new ProductDTO(
                product.getBrand(),
                product.getCategory(),
                product.getDescription(),
                product.getId(),
                product.getImageData(),
                product.getImageName(),
                product.getImageType(),
                product.getName(),
                product.getPrice(),
                product.isProductAvailable(),
                product.getReleaseDate(),
                product.getStockQuantity()
        );
    }
    public UserInfoDTO toDTO(UserInfo user) {
        if(user == null)
            return null;
        return new UserInfoDTO(
                user.getUser_id(),
                user.getUser_name(),
                user.getUser_dp_path(),
                user.getUserEmail()
        );
    }
    public  Product toEntity(ProductDTO productDTO) {
        if(productDTO == null)
            return null;

        return new Product(
                productDTO.getBrand(),
                productDTO.getCategory(),
                productDTO.getDescription(),
                productDTO.getId(),
                productDTO.getImageData(),
                productDTO.getImageName(),
                productDTO.getImageType(),
                productDTO.getImageName(),
                productDTO.getPrice(),
                productDTO.isProductAvailable(),
                productDTO.getReleaseDate(),
                productDTO.getStockQuantity()

        );


    }
    public UserInfo toRegisteredUser(UserCreateDTO register )
    {
        if(register == null)
            return null;
        UserInfo userInfo = new UserInfo(
                register.getUserEmail(),
                register.getUserName(),
                passwordEncoder.encode(register.getUserPassword())
        );
        Role role = roleRepo.findById(Integer.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        userInfo.setRole(role);

        return userInfo;

    }


    public  List<ProductDTO> toProductDTOList(List<Product> products) {
        if (products == null) {
            return List.of();
        }
        return products.stream()
                .map(this::toDTO)
                .toList();
    }
    public List<UserInfoDTO> toUserInfoDTOList(List<UserInfo> userInfos) {
        if (userInfos == null) {
            return List.of();
        }
        return userInfos.stream()
                .map(this::toDTO)
                .toList();
    }



}
