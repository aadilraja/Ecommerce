package application.server.Validation.validator;

import application.server.Validation.annotation.PasswordMatches;
import application.server.persistence.DTOs.UserCreateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class passwordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserCreateDTO user = (UserCreateDTO) obj;
        return user.getUserPassword().equals(user.getUserMatchingPassword());
    }
}
