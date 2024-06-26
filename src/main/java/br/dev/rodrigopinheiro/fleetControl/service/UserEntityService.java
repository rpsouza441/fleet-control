package br.dev.rodrigopinheiro.fleetControl.service;

import br.dev.rodrigopinheiro.fleetControl.controller.dto.CreateUserDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.LoginUserDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.RecoveryJwtTokenDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.UserDto;
import br.dev.rodrigopinheiro.fleetControl.entity.Role;
import br.dev.rodrigopinheiro.fleetControl.entity.UserEntity;
import br.dev.rodrigopinheiro.fleetControl.exception.FleetControlException;
import br.dev.rodrigopinheiro.fleetControl.exception.UserNotFoundByEmailException;
import br.dev.rodrigopinheiro.fleetControl.exception.UserNotFoundException;
import br.dev.rodrigopinheiro.fleetControl.repository.UserRepository;
import br.dev.rodrigopinheiro.fleetControl.security.SecurityConfiguration;
import br.dev.rodrigopinheiro.fleetControl.security.UserDetailsImpl;
import br.dev.rodrigopinheiro.fleetControl.security.service.JwtTokenService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

   private SecurityConfiguration securityConfiguration;

    public UserEntityService(UserRepository userRepository,
                             AuthenticationManager authenticationManager,
                             JwtTokenService jwtTokenService,
                             SecurityConfiguration securityConfiguration) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.securityConfiguration = securityConfiguration;
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundByEmailException(email));
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }


    public UserEntity findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    //TODO Password
    public UserDto create(UserDto userDto) {

        var user = userDto.toUser();
        var userCreated = userRepository.save(user);
        return new UserDto( userCreated.getEmail(), userCreated.getPassword(), userCreated.getActive());

    }

    public List<UserDto> findAll() {

        List<UserEntity> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserDto(user.getEmail(), user.getPassword(), user.getActive()))
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return new UserDto(user.getEmail(), user.getPassword(), user.getActive());
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        } catch (Exception e) {
            throw new FleetControlException();
        }
    }

    public UserDto update(Long id, UserDto userDto) {
        var updatedUser = userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDto.email());
            existingUser.setEmail(userDto.password());
            existingUser.setActive(userDto.active());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new UserNotFoundException(id));
        return userDto.fromUser(updatedUser);
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        UserEntity newUser = UserEntity.builder()
                .email(createUserDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
}
