package org.demo.springai.controller;

import org.demo.springai.model.Answer;
import org.demo.springai.model.Question;
import org.demo.springai.service.RecipeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    @Autowired
    private RecipeGenerator recipeGeneratorService;

    @GetMapping("/generate-recipe")
    public Answer generateRecipe(@RequestParam(value="question", defaultValue="What are the ingredients")String question,
                                 @RequestParam(value="foodName", defaultValue="pizza")  String foodName) {
        return recipeGeneratorService.generateRecipe(new Question(question,foodName));
    }
}

