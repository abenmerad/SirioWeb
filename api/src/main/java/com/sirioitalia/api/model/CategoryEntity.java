package com.sirioitalia.api.model;

import javax.persistence.*;

@Entity
@Table
public class CategoryEntity {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Integer id;
    private String label;

    public CategoryEntity() {
    }

    public CategoryEntity(Integer id,
                          String label) {
        this.id = id;
        this.label = label;
    }

    public CategoryEntity(String label) {
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
