package ru.job4j.util;

import ru.job4j.todo.model.User;

import javax.servlet.http.HttpSession;

public class UserAdditional {

    private UserAdditional() {

    }

    public static User getFromHttpSession(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User(0, "", "", "");
            user.setName("Гость");
        }
        return user;
    }

}
