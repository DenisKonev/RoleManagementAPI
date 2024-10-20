package io.github.deniskonev.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequestDTO {

    @NotNull(message = "Название роли не должно быть пустым")
    @Size(max = 50, message = "Название роли не должно превышать 50 символов")
    private String name;

    @Size(max = 255, message = "Описание роли не должно превышать 255 символов")
    private String description;
}
