package com.sirioitalia.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "colors")
public class Color {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    @Setter
    @Column(name = "label", nullable = false, unique = true)
    private String label;

    @Getter
    @Setter
    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "hexadecimalCode", nullable = false, unique = true)
    private String hexadecimalCode;

    @ManyToMany(mappedBy = "appliedColors")
    Set<Item> items;
}
