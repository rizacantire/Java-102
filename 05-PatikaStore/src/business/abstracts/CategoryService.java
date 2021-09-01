package business.abstracts;

import entities.Category;

public interface CategoryService {
    void addCategory(Category category);
    void listCategory();
    void listCategoryById(int categoryId);
}
