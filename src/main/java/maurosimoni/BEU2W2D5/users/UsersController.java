package maurosimoni.BEU2W2D5.users;

import maurosimoni.BEU2W2D5.exception.NotFoundException;
import maurosimoni.BEU2W2D5.users.payload.UserCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    // testata OK
    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.find(page, size, sortBy);
    }
    // testata OK
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated UserCreatePayload body) {
        return usersService.create(body);
    }
    // testata OK
    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) throws Exception {
        return usersService.findById(userId);
    }
    // testata OK
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable UUID userId, @RequestBody User body) throws Exception {
        return usersService.findByIdAndUpdate(userId, body);
    }
    // testata OK
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        usersService.findByIdAndDelete(userId);
    }

}
