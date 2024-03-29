package org.talent.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.talent.todolist.domain.HttpResponse;
import org.talent.todolist.dto.NewCategoryRequest;
import org.talent.todolist.entity.Category;
import org.talent.todolist.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryService.findAll();

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryId(@PathVariable Long id){
        Category category = categoryService.findById(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<HttpResponse> saveNewCategory(@RequestBody NewCategoryRequest request){
        Category category = categoryService.NewCategory(request);
        HttpResponse httpResponse = new HttpResponse(category, HttpStatus.CREATED);

        return new ResponseEntity<>(httpResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.delete(id);

        return new ResponseEntity<>("Author Deleted By Id " + id, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody NewCategoryRequest request){
        Category category = categoryService.updateById(id, request);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
