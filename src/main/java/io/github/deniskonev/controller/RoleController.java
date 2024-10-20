package io.github.deniskonev.controller;

import io.github.deniskonev.dto.RoleRequestDTO;
import io.github.deniskonev.dto.RoleResponseDTO;
import io.github.deniskonev.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static io.github.deniskonev.config.ApiConstants.BASE_API;
import static io.github.deniskonev.config.ApiConstants.ROLES;

@Slf4j
@RestController
@RequestMapping(BASE_API + ROLES)
@Tag(name = "Role Controller", description = "CRUD операции для ролей")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Получение всех ролей.
     *
     * @return список RoleResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        log.debug("Входящий запрос: GET /{}", BASE_API + ROLES);
        List<RoleResponseDTO> roleDTOs = roleService.getAllRoles();
        log.debug("Возвращено {} ролей", roleDTOs.size());
        return ResponseEntity.ok(roleDTOs);
    }

    /**
     * Получение роли по идентификатору.
     *
     * @param id идентификатор роли
     * @return RoleResponseDTO или статус 404, если роль не найдена
     */
    @GetMapping("/{id}")
    @SuppressWarnings("LoggingSimilarMessage")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
        log.debug("Входящий запрос: GET /{}/{}", BASE_API + ROLES, id);
        Optional<RoleResponseDTO> roleDTOOptional = roleService.getRoleById(id);
        if (roleDTOOptional.isPresent()) {
            log.debug("Роль с id {} найдена", id);
            return ResponseEntity.ok(roleDTOOptional.get());
        } else {
            log.debug("Роль с id {} не найдена", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Создание новой роли.
     *
     * @param roleDTO данные для создания роли
     * @return RoleResponseDTO созданной роли
     */
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleRequestDTO roleDTO) {
        log.debug("Входящий запрос: POST /{} с данными {}", BASE_API + ROLES, roleDTO);
        RoleResponseDTO responseDTO = roleService.createRole(roleDTO);
        log.debug("Роль создана с id {}", responseDTO.getId());
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Обновление существующей роли.
     *
     * @param id      идентификатор роли
     * @param roleDTO новые данные роли
     * @return RoleResponseDTO обновленной роли или статус 404, если роль не найдена
     */
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO roleDTO) {
        log.debug("Входящий запрос: PUT /{}/{} с данными {}", BASE_API + ROLES, id, roleDTO);
        Optional<RoleResponseDTO> updatedRoleDTO = roleService.updateRole(id, roleDTO);
        if (updatedRoleDTO.isPresent()) {
            log.debug("Роль с id {} успешно обновлена", id);
            return ResponseEntity.ok(updatedRoleDTO.get());
        } else {
            log.debug("Роль с id {} не найдена для обновления", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаление роли по идентификатору.
     *
     * @param id идентификатор роли
     * @return статус 204 если удалена, 404 если не найдена
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.debug("Входящий запрос: DELETE /{}/{}", BASE_API + ROLES, id);
        boolean isDeleted = roleService.deleteRole(id);
        if (isDeleted) {
            log.debug("Роль с id {} успешно удалена", id);
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Роль с id {} не найдена для удаления", id);
            return ResponseEntity.notFound().build();
        }
    }
}
