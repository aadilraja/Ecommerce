package application.server.persistence.Repo;

import application.server.persistence.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo,Long> {
    Boolean existsByUserEmail(String userEmail);
    UserInfo findByUserEmail(String userEmail);
}
