package io.github.deniskonev.service;

import io.github.deniskonev.dto.RoleRequestDTO;
import io.github.deniskonev.dto.RoleResponseDTO;
import io.github.deniskonev.model.Role;
import io.github.deniskonev.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//TODO Починить тесты
@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role roleEntity;
    private RoleResponseDTO roleResponseDTO;
    private RoleRequestDTO roleRequestDTO;

    @BeforeEach
    void setUp() {
        roleEntity = new Role();
        roleEntity.setId(1L);
        roleEntity.setName("ADMIN");
        roleEntity.setDescription("Администратор системы");

        roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setId(1L);
        roleResponseDTO.setName("ADMIN");
        roleResponseDTO.setDescription("Администратор системы");

        roleRequestDTO = new RoleRequestDTO();
        roleRequestDTO.setName("ADMIN");
        roleRequestDTO.setDescription("Администратор системы");
    }

    @Test
    @DisplayName("Get all roles")
    void testGetAllRoles() {
        when(roleRepository.findAll()).thenReturn(Arrays.asList(roleEntity));

        List<RoleResponseDTO> result = roleService.getAllRoles();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualToComparingFieldByField(roleResponseDTO);

        verify(roleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Get role by id: Success")
    void testGetRoleById_Found() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));

        Optional<RoleResponseDTO> result = roleService.getRoleById(1L);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualToComparingFieldByField(roleResponseDTO);

        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Get role by id: Not found")
    void testGetRoleById_NotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<RoleResponseDTO> result = roleService.getRoleById(1L);

        assertThat(result).isNotPresent();

        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Create role: Success")
    void testCreateRole_Success() {
        when(roleRepository.existsByName("ADMIN")).thenReturn(false);
        when(roleRepository.save(any(Role.class))).thenReturn(roleEntity);

        RoleResponseDTO result = roleService.createRole(roleRequestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualToComparingFieldByField(roleResponseDTO);

        verify(roleRepository, times(1)).existsByName("ADMIN");
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    @DisplayName("Create role: Duplicate name")
    void testCreateRole_DuplicateName() {
        when(roleRepository.existsByName("ADMIN")).thenReturn(true);

        try {
            roleService.createRole(roleRequestDTO);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Роль с таким названием уже существует");
        }

        verify(roleRepository, times(1)).existsByName("ADMIN");
        verify(roleRepository, times(0)).save(any(Role.class));
    }

    @Test
    @DisplayName("Update role: Success")
    void testUpdateRole_Found() {
        Role updatedRole = new Role();
        updatedRole.setId(1L);
        updatedRole.setName("ADMIN");
        updatedRole.setDescription("Обновлённое описание");

        RoleResponseDTO updatedDTO = new RoleResponseDTO();
        updatedDTO.setId(1L);
        updatedDTO.setName("ADMIN");
        updatedDTO.setDescription("Обновлённое описание");

        RoleRequestDTO updateDTO = new RoleRequestDTO();
        updateDTO.setName("ADMIN");
        updateDTO.setDescription("Обновлённое описание");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));
        when(roleRepository.save(any(Role.class))).thenReturn(updatedRole);

        Optional<RoleResponseDTO> result = roleService.updateRole(1L, updateDTO);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualToComparingFieldByField(updatedDTO);

        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).save(roleEntity);
    }

    @Test
    @DisplayName("Update role: Not found")
    void testUpdateRole_NotFound() {
        RoleRequestDTO updateDTO = new RoleRequestDTO();
        updateDTO.setName("ADMIN");
        updateDTO.setDescription("Обновлённое описание");

        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<RoleResponseDTO> result = roleService.updateRole(1L, updateDTO);

        assertThat(result).isNotPresent();

        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(0)).save(any(Role.class));
    }

    @Test
    @DisplayName("Delete role: Success")
    void testDeleteRole_Found() {
        when(roleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(roleRepository).deleteById(1L);

        boolean result = roleService.deleteRole(1L);

        assertThat(result).isTrue();

        verify(roleRepository, times(1)).existsById(1L);
        verify(roleRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete role: Not found")
    void testDeleteRole_NotFound() {
        when(roleRepository.existsById(1L)).thenReturn(false);

        boolean result = roleService.deleteRole(1L);

        assertThat(result).isFalse();

        verify(roleRepository, times(1)).existsById(1L);
        verify(roleRepository, times(0)).deleteById(anyLong());
    }
}
