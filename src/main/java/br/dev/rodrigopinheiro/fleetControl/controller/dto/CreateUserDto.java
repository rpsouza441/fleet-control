package br.dev.rodrigopinheiro.fleetControl.controller.dto;

import br.dev.rodrigopinheiro.fleetControl.entity.enums.RoleName;

public record CreateUserDto(
        String name,
        String email,
        String password
) {
}
