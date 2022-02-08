package upgrade.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upgrade.api.dto.UserRequest;
import upgrade.api.services.interfaces.IUsersServices;
import upgrade.api.utils.exceptions.ApiUnprocessableEntity;
import upgrade.api.validators.UserValidatorImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class ApiController {

    @Autowired
    private IUsersServices usersServices;

    @Autowired
    private UserValidatorImpl userValidator;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(this.usersServices.findAll());
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.usersServices.findByUsername(username));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUser(@RequestBody UserRequest request) throws ApiUnprocessableEntity {
        this.userValidator.validator(request);
        this.usersServices.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/delete/{id}" )
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int userId) {
        this.usersServices.deleteById(userId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequest request, @PathVariable("id") int userId){
        this.usersServices.update(request, userId);
        return ResponseEntity.ok(Boolean.TRUE);
    }


}
