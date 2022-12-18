package ru.job4j.todo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;

public class StaticSpring {
    private static SessionFactory sf;
    /*
    написано, что в приложении нужно использовать только 1 объект SessionFactory
    По этой причинее метод сделан статичным
     */
    @Bean(destroyMethod = "close")
    public static SessionFactory sf() {
        if (sf == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return sf;
    }
}
