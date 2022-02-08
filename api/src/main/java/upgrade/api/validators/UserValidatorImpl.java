package upgrade.api.validators;

import org.springframework.stereotype.Component;
import upgrade.api.dto.UserRequest;
import upgrade.api.utils.exceptions.ApiUnprocessableEntity;

@Component
public class UserValidatorImpl implements UserValidator {

    @Override
    public void validator(UserRequest request) throws ApiUnprocessableEntity {
        if (request.getFirstname() == null || request.getFirstname().isEmpty()){
            this.message("El nombre es obligatorio");
        }
        if (request.getLastname() == null || request.getLastname().isEmpty()){
            this.message("El apellido es obligatorio");
        }
        if (request.getUsername() == null || request.getUsername().isEmpty()){
            this.message("El username no puede estar vacío");
        }
        if (request.getUsername().length() <= 4){
            this.message("El username debe ser mayor que 4 caracteres");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()){
            this.message("La contraseña no puede estar vacío");
        }
        if (request.getPassword().length() <= 6){
            this.message("La contraseña debe ser mayor que 6 caracteres");
        }
    }

    private void message(String message) throws ApiUnprocessableEntity {
        throw new ApiUnprocessableEntity(message);
    }
}
