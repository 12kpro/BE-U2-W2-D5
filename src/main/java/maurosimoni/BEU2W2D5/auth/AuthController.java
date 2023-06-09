package maurosimoni.BEU2W2D5.auth;

import maurosimoni.BEU2W2D5.auth.payloads.AuthenticationSuccessfullPayload;
import maurosimoni.BEU2W2D5.exception.NotFoundException;
import maurosimoni.BEU2W2D5.exception.UnauthorizedException;
import maurosimoni.BEU2W2D5.users.User;
import maurosimoni.BEU2W2D5.users.UsersService;
import maurosimoni.BEU2W2D5.users.payload.UserLoginPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
            throws NotFoundException {

        User user = usersService.findByUserName(body.getUserName());
        if (!body.getPassword().matches(user.getPassword()))
            throw new UnauthorizedException("Credenziali non valide");
        String token = JWTTools.createToken(user);
        return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
    }

}
