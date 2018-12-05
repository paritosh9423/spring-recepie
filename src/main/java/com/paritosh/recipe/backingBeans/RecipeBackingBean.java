package com.paritosh.recipe.backingBeans;

import com.paritosh.recipe.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeBackingBean {


    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientBackingBean> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesBackingBean notes;
    private Set<CategoryBackingBean> categories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}



