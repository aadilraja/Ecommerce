package application.server.Service;


import application.server.persistence.model.UserInfo;
import application.server.persistence.model.VerificationToken;
import application.server.exception.UserRegistrationException;

public interface IUserService {


        UserInfo registerNewUserAccount(UserInfo user)
                throws UserRegistrationException;

        UserInfo getUser(String verificationToken);

        void saveRegisteredUser(UserInfo user);

        void createVerificationToken(UserInfo user, String token);

        VerificationToken getVerificationToken(String VerificationToken);


}
