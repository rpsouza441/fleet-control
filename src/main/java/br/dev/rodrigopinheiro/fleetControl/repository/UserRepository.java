package br.dev.rodrigopinheiro.fleetControl.repository;

import br.dev.rodrigopinheiro.fleetControl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
}
