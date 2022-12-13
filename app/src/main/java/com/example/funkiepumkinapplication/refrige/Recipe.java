package com.example.funkiepumkinapplication.refrige;

import java.io.Serializable;

public class Recipe implements Serializable {
    private Integer recipeId;
    private String recipeName;
    private String recipeIngredient;
    private String recipeCookingorder;
    private String recipeImg;

    public Recipe(Integer recipeId, String recipeName, String recipeIngredient, String recipeCookingorder, String recipeImg) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredient = recipeIngredient;
        this.recipeCookingorder = recipeCookingorder;
        this.recipeImg = recipeImg;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(String recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public String getRecipeCookingorder() {
        return recipeCookingorder;
    }

    public void setRecipeCookingorder(String recipeCookingorder) {
        this.recipeCookingorder = recipeCookingorder;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }
}

