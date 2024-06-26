package br.dev.rodrigopinheiro.fleetControl.security.service;

import br.dev.rodrigopinheiro.fleetControl.entity.UserEntity;
import br.dev.rodrigopinheiro.fleetControl.exception.UserNotFoundByEmailException;
import br.dev.rodrigopinheiro.fleetControl.repository.UserRepository;
import br.dev.rodrigopinheiro.fleetControl.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundByEmailException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundByEmailException(username));
        return new UserDetailsImpl(user);
    }
}
