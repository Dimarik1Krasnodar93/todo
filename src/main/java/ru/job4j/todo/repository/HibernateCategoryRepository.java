package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class HibernateCategoryRepository implements CategoryRepository {
    private final SessionFactory sessionFactory;
    private final CrudRepository crudRepository;

    public static final String FIND_ALL_CATEGORIES = "SELECT t FROM Category AS t";

    @Override
    public List<Category> findAll() {
        return crudRepository.query(FIND_ALL_CATEGORIES, Category.class, new HashMap<>());
    }

    @Override
    public List<Category> findByListId(List<Integer> list) {
        String query = "SELECT t from Category AS t where t.id IN :list";
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        return crudRepository.query(query, Category.class, map);
    }
}
