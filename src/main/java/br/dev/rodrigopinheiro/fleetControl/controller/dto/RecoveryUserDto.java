package br.dev.rodrigopinheiro.fleetControl.controller.dto;

import br.dev.rodrigopinheiro.fleetControl.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
