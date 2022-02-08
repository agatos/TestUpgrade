package upgrade.api.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upgrade.api.dto.UserRequest;
import upgrade.api.dto.UsersDTO;
import upgrade.api.entities.Users;
import upgrade.api.repositories.UsersRepository;
import upgrade.api.services.interfaces.IUsersServices;
import upgrade.api.utils.hash.BCrypt;
import upgrade.api.utils.helpers.MHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersImpl implements IUsersServices {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersDTO> findAll() {
        List<UsersDTO> dto = new ArrayList<>();
        Iterable<Users> users = this.usersRepository.findAll();

        for (Users user : users) {
            UsersDTO usersDTO = MHelpers.modelMapper().map(user, UsersDTO.class);
            dto.add(usersDTO);
        }
        return dto;
    }

    @Override
    public UsersDTO findByUsername(String username) {
        Optional<Users> users = this.usersRepository.findByUsername(username);
        if (!users.isPresent()){
            return null;
        }
        return MHelpers.modelMapper().map(users.get(), UsersDTO.class);
    }

    @Override
    public UsersDTO findById(int userId) {
        Optional<Users> users = this.usersRepository.findById(userId);
        if (!users.isPresent()){
            return null;
        }
        return MHelpers.modelMapper().map(users.get(), UsersDTO.class);
    }

    @Override
    public void save(UserRequest user) {

        Users users = MHelpers.modelMapper().map(user, Users.class);

        users.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        this.usersRepository.save(users);
    }

    @Override
    public void saveAll(List<UserRequest> users) {

        List<Users> u = new ArrayList<>();
        for (UserRequest user : users) {
            Users us = MHelpers.modelMapper().map(user, Users.class);
            u.add(us);
        }

        this.usersRepository.saveAll(u);
    }

    @Override
    public void deleteById(int userId) {

        this.usersRepository.deleteById(userId);

    }

    @Override
    public void update(UserRequest request, int userId) {
        Optional<Users> users = this.usersRepository.findById(userId);
        Users user = users.get();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());

        if (!request.getPassword().isEmpty()) {

            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        this.usersRepository.save(user);
    }

    private UsersDTO convertToUserDTO(final Users users){
        return MHelpers.modelMapper().map(users, UsersDTO.class);
    }

}
