package upgrade.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upgrade.api.entities.Users;

import java.util.Optional;


@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    @Transactional(readOnly = true)
    Optional<Users> findByUsername(String username);


}
