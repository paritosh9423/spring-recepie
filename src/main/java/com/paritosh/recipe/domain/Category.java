package com.paritosh.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipeSet"})//(overriding because lombok is creating hascode and bidirectional mapping is causing stackoverflow error)
@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipeSet = new HashSet<>();

}
