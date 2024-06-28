package br.dev.rodrigopinheiro.fleetControl.service;

import br.dev.rodrigopinheiro.fleetControl.controller.dto.RoleDto;
import br.dev.rodrigopinheiro.fleetControl.entity.Role;
import br.dev.rodrigopinheiro.fleetControl.entity.enums.RoleName;
import br.dev.rodrigopinheiro.fleetControl.exception.RoleNotFoundException;
import br.dev.rodrigopinheiro.fleetControl.exception.FleetControlException;
import br.dev.rodrigopinheiro.fleetControl.repository.RoleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto create(RoleDto roleDto) {
        var role = roleDto.toRole();
        var roleCreated = roleRepository.save(role);
        return new RoleDto(roleCreated.getName());
    }

    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> new RoleDto(role.getName()))
                .collect(Collectors.toList());
    }

    public RoleDto findById(Long id) {
        var role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));

        return new RoleDto(role.getName());
    }

    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RoleNotFoundException(id);
        } catch (Exception e) {
            throw new FleetControlException();
        }
    }

    public RoleDto update(Long id, RoleDto roleDto) {
        var updatedRole = roleRepository.findById(id).map(existingRole -> {
            existingRole.setName(roleDto.name());
            return roleRepository.save(existingRole);
        }).orElseThrow(() -> new RoleNotFoundException(id));
        return roleDto.fromRole(updatedRole);
    }

}
