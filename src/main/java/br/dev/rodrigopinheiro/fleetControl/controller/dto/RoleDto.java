package br.dev.rodrigopinheiro.fleetControl.controller.dto;

import br.dev.rodrigopinheiro.fleetControl.entity.Role;
import br.dev.rodrigopinheiro.fleetControl.entity.enums.RoleName;

public record RoleDto(
        RoleName name
) {
    public Role toRole() {
        return Role.builder()
                .name(name)
                .build();
    }

    public RoleDto fromRole(Role role) {
        return new RoleDto(role.getName());
    }
}
