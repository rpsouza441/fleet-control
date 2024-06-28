package br.dev.rodrigopinheiro.fleetControl.repository;

import br.dev.rodrigopinheiro.fleetControl.entity.Role;
import br.dev.rodrigopinheiro.fleetControl.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Optional<Role> findByName(RoleName name);
}
