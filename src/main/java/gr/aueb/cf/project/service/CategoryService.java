package gr.aueb.cf.project.service;

import gr.aueb.cf.project.model.Category;
import gr.aueb.cf.project.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private  final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> listCategories() {
        return categoryRepo.findAll();
    }

    public Category readCategory(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) {

        Category category = categoryRepo.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepo.save(category);
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

    public void deleteById(int categoryId) {
         categoryRepo.deleteById(categoryId);
    }

}