package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final SessionFactory sf;
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesByListId(List<Integer> listId) {
        if (listId.isEmpty()) {
            return new ArrayList<>();
        }
        return categoryRepository.findByListId(listId);
    }
}
