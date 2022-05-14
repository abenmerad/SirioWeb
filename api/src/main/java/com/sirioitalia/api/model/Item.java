package com.sirioitalia.api.model;

import com.sirioitalia.api.embeddable.Dimension;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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

    @NotNull
    @Getter
    @Setter
    @Embedded
    private Dimension dimension;

    @Getter
    @Setter
    @Positive
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

    @Getter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Image> images = new ArrayList<>();

}
