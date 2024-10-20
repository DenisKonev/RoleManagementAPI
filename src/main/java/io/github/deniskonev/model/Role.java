package io.github.deniskonev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "uc_role_name")
})
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Название роли не должно быть пустым")
    @Size(max = 50, message = "Название роли не должно превышать 50 символов")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 255, message = "Описание роли не должно превышать 255 символов")
    @Column(length = 255)
    private String description;
}
