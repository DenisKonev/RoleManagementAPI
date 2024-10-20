package io.github.deniskonev.service;

import io.github.deniskonev.dto.RoleRequestDTO;
import io.github.deniskonev.dto.RoleResponseDTO;
import io.github.deniskonev.model.Role;
import io.github.deniskonev.repository.RoleRepository;
import io.github.deniskonev.util.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Получение всех ролей.
     *
     * @return список RoleResponseDTO
     */
    public List<RoleResponseDTO> getAllRoles() {
        log.debug("Запрос на получение всех ролей");
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDTO> roleDTOs = roles.stream()
                .map(RoleMapper::toDTO)
                .toList();
        log.debug("Найдено {} ролей", roleDTOs.size());
        return roleDTOs;
    }

    /**
     * Получение роли по идентификатору.
     *
     * @param id идентификатор роли
     * @return Optional с RoleResponseDTO или пустой Optional, если роль не найдена
     */
    public Optional<RoleResponseDTO> getRoleById(Long id) {
        log.debug("Запрос на получение роли с id {}", id);
        Optional<RoleResponseDTO> roleDTOOptional = roleRepository.findById(id)
                .map(RoleMapper::toDTO);
        if (roleDTOOptional.isPresent()) {
            log.debug("Роль с id {} найдена", id);
        } else {
            log.debug("Роль с id {} не найдена", id);
        }
        return roleDTOOptional;
    }

    /**
     * Создание новой роли.
     *
     * @param roleDTO данные для создания роли
     * @return RoleResponseDTO созданной роли
     */
    public RoleResponseDTO createRole(RoleRequestDTO roleDTO) {
        log.debug("Запрос на создание роли: {}", roleDTO);
        if (roleRepository.existsByName(roleDTO.getName())) {
            log.debug("Роль с названием {} уже существует", roleDTO.getName());
            throw new IllegalArgumentException("Роль с таким названием уже существует");
        }
        Role role = RoleMapper.toEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        RoleResponseDTO responseDTO = RoleMapper.toDTO(savedRole);
        log.debug("Роль создана с id {}", responseDTO.getId());
        return responseDTO;
    }

    /**
     * Обновление существующей роли.
     *
     * @param id      идентификатор роли
     * @param roleDTO новые данные роли
     * @return Optional с обновленным RoleResponseDTO или пустой Optional, если роль не найдена
     */
    public Optional<RoleResponseDTO> updateRole(Long id, RoleRequestDTO roleDTO) {
        log.debug("Запрос на обновление роли с id {}: {}", id, roleDTO);
        return roleRepository.findById(id)
                .map(role -> {
                    RoleMapper.updateEntity(role, roleDTO);
                    Role updatedRole = roleRepository.save(role);
                    RoleResponseDTO responseDTO = RoleMapper.toDTO(updatedRole);
                    log.debug("Роль с id {} обновлена", id);
                    return responseDTO;
                });
    }

    /**
     * Удаление роли по идентификатору.
     *
     * @param id идентификатор роли
     * @return true если роль была удалена, false если роль не найдена
     */
    public boolean deleteRole(Long id) {
        log.debug("Запрос на удаление роли с id {}", id);
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            log.debug("Роль с id {} успешно удалена", id);
            return true;
        }
        log.debug("Роль с id {} не найдена для удаления", id);
        return false;
    }
}
