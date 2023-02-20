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
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        StringBuilder query = new StringBuilder("SELECT t from Category AS t where t.id = :id0");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id0", list.get(0));
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                query.append(" or i.id = :id");
                query.append(i);
                map.put("id" + i, Integer.toString(i));
            }
        }
        return crudRepository.query(query.toString(), Category.class, map);
    }
}
