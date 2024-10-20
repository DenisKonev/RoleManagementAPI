package io.github.deniskonev.util;

import io.github.deniskonev.dto.RoleRequestDTO;
import io.github.deniskonev.dto.RoleResponseDTO;
import io.github.deniskonev.model.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleMapper {

    public static Role toEntity(RoleRequestDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return role;
    }

    public static RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }

    public static void updateEntity(Role role, RoleRequestDTO dto) {
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
    }
}
