package services.category;

import models.Category;
import repositories.category.CategoryRepository;
import repositories.category.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService {
    private final ICategoryRepository repo = new CategoryRepository();

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Category getById(int id) {
        return repo.findById(id);
    }

    @Override
    public boolean add(Category category) {
        return repo.save(category);
    }

    @Override
    public boolean update(Category category) {
        return repo.update(category);
    }

    @Override
    public boolean delete(int id) {
        return repo.delete(id);
    }
}
