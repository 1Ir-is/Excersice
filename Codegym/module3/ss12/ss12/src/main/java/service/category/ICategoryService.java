package service.category;

import model.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(int id);
    void save(Category category);
    void update(Category category);
    void delete(int id);
}
