package core;

import entity.User;

public class ComboItem {
    private  int key ;
    private  String value ;

    public ComboItem(int key, User.Role value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}

