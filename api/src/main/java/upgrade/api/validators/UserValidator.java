package upgrade.api.validators;

import org.springframework.stereotype.Service;
import upgrade.api.dto.UserRequest;
import upgrade.api.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface UserValidator {

    void validator(UserRequest request) throws ApiUnprocessableEntity;

}
