package com.intership.infrastructure.controllers;

import com.intership.infrastructure.payload.dto.CategoryAppDTO;
import com.intership.infrastructure.services.CategoryAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-apps")
@RequiredArgsConstructor
public class CategoryAppController {

    private final CategoryAppService categoryAppService;

    @PostMapping
    public ResponseEntity<CategoryAppDTO> createCategoryApp(@RequestBody CategoryAppDTO categoryAppDTO) {
        return ResponseEntity.ok(categoryAppService.createCategoryApp(categoryAppDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryAppDTO> getCategoryAppById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryAppService.getCategoryAppById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryAppDTO>> getAllCategoryApps() {
        return ResponseEntity.ok(categoryAppService.getAllCategoryApps());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryAppDTO> updateCategoryApp(@PathVariable Integer id, @RequestBody CategoryAppDTO categoryAppDTO) {
        return ResponseEntity.ok(categoryAppService.updateCategoryApp(id, categoryAppDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryApp(@PathVariable Integer id) {
        categoryAppService.deleteCategoryApp(id);
        return ResponseEntity.noContent().build();
    }
}
