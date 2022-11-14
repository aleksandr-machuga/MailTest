package by.mailru.enums;

import by.mailru.entity.User;

public enum TestUsers {
    SOME_TEST(new User("Some Test", "some.test.51", "wedding210707", "some.test.51@mail.ru"));

    private User user;

    TestUsers(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
