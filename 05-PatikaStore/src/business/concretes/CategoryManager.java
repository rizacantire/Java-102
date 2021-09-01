package business.concretes;

import business.abstracts.CategoryService;
import entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager implements CategoryService {
    List<Category> categories = new ArrayList<>();
    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void listCategory() {

    }

    @Override
    public void listCategoryById(int categoryId) {

    }
}
