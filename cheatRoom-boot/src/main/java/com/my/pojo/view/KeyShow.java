package com.my.pojo.view;

import com.my.pojo.base.Key;
import com.my.pojo.base.User;

/**
 * @author w
 */
public class KeyShow {
    private Key key;
    private User user;

    public KeyShow(Key key, User user) {
        this.key = key;
        this.user = user;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
