package upgrade.api.services.interfaces;

import org.springframework.stereotype.Service;
import upgrade.api.dto.UserRequest;
import upgrade.api.dto.UsersDTO;

import java.util.List;

@Service
public interface IUsersServices {

    List<UsersDTO> findAll();

    UsersDTO findByUsername(String username);

    UsersDTO findById(int userId);

    void save(UserRequest user);

    void saveAll(List<UserRequest> users);

    void deleteById(int userId);

    void update(UserRequest user, int userId);

}