package com.sirioitalia.api.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.ElementCollection;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "items")
public class Item implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String label;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(nullable = false)
    private String description;

    @Getter
    @Setter
    @Positive
    @Column
    private double price;

    @Getter
    @Setter
    @Positive
    @Column
    private double width;

    @Getter
    @Setter
    @Positive
    @Column(nullable = false)
    private double length;

    @Getter
    @Setter
    @Positive
    @Column(nullable = false)
    private double height;

    @Getter
    @Setter
    @Positive
    @Column(nullable = false)
    private double weight;

    @PositiveOrZero
    @Column(name = "stock", nullable = false)
    private int stock;

    @Getter
    @Setter
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "\"categoryId\"", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Category category;

    @ManyToMany
    @JoinTable(name = "items_colors",
            joinColumns = @JoinColumn(name = "\"itemId\""),
            inverseJoinColumns = @JoinColumn(name = "\"colorId\"", referencedColumnName = "id"))
    private Collection<Color> appliedColors = new java.util.ArrayList<>();
}
