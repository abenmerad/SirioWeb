package com.sirioitalia.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sirioitalia.api.embeddable.Dimension;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @Column(unique = true, updatable = false)
    @NotEmpty
    @NotNull
    private String reference;

    @PositiveOrZero
    @Getter
    @Setter
    @Column(name = "stock", nullable = false)
    private int stock;

    @Getter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Image> images = new ArrayList<>();

    @Getter
    @Setter
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "\"furnitureId\"", nullable = false)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Furniture furniture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    @JoinTable(
            name = "items_colors",
            joinColumns = @JoinColumn(name = "\"itemId\""),
            inverseJoinColumns = @JoinColumn(name = "\"colorId\"", referencedColumnName = "id")
    )
    private List<Color> color = new ArrayList<>();
}
