package application.server.Service;

import application.server.persistence.Repo.TokenRepo;
import application.server.persistence.Repo.UserRepo;
import application.server.persistence.mapper.Mapper;
import application.server.persistence.model.UserInfo;
import application.server.persistence.model.VerificationToken;
import application.server.exception.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService {
    UserRepo userRepo;
    Mapper mapper;
    TokenRepo tokenRepo;

    @Autowired
    public UserService(UserRepo userRepo, Mapper mapper, TokenRepo tokenRepo)
    {
        this.userRepo = userRepo;
        this.mapper=mapper;
        this.tokenRepo = tokenRepo;

    }
    public UserInfo registerNewUserAccount(UserInfo user) throws UserRegistrationException {
        if(userRepo.existsByUserEmail(user.getUserEmail()))
        {
            System.out.println("User already exists");
            throw new UserRegistrationException("User with following email already exists: "+user.getUserEmail());
        }
        return userRepo.save(user);


    }
    @Override
    public UserInfo getUser(String verificationToken)
    {
        return tokenRepo.findByToken(verificationToken).getUser();

    }
    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepo.findByToken(VerificationToken);
    }
    @Override
    public void saveRegisteredUser(UserInfo user) {
        userRepo.save(user);
    }

    @Override
    public void createVerificationToken(UserInfo user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepo.save(myToken);
    }


}
