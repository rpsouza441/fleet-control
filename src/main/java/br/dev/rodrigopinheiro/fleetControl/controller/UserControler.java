package br.dev.rodrigopinheiro.fleetControl.controller;

import br.dev.rodrigopinheiro.fleetControl.controller.dto.CreateUserDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.LoginUserDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.RecoveryJwtTokenDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.UserDto;
import br.dev.rodrigopinheiro.fleetControl.entity.UserEntity;
import br.dev.rodrigopinheiro.fleetControl.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControler {

    private final UserEntityService userEntityService;

    public UserControler(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDTO) {
        if (!userDTO.email().endsWith("@xyz.com") && !userDTO.email().endsWith("@rps.com")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userEntityService.create(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userEntityService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        userEntityService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/user")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }

}
