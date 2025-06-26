package application.server.persistence.Repo;

import application.server.persistence.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserInfo,Long> {
    Boolean existsByUserEmail(String userEmail);
    @Query("SELECT u FROM UserInfo u WHERE LOWER(u.userEmail) = LOWER(:email)")
    Optional<UserInfo> findByUserEmail(@Param("email") String email);

}
