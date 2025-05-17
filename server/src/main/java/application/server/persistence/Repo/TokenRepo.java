package application.server.persistence.Repo;

import application.server.persistence.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo extends CrudRepository<VerificationToken,Long> {
   VerificationToken findByToken(String verificationToken);
}
