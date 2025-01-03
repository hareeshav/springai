package org.demo.springai.service;


import org.demo.springai.model.Answer;
import org.demo.springai.model.Question;

public interface RecipeGenerator {
    public Answer generateRecipe(Question question);
}
