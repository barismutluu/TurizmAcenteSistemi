package core;

import entity.User;


// Combination element class represents a key and value pair
public class ComboItem {
    private int key;
    private String value;

    // Parameterized constructor
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem() {

    }

    //Method that returns the key value
    public int getKey() {
        return key;
    }

    //Method that sets the key value
    public void setKey(int key) {
        this.key = key;
    }

    //Method that returns the  value
    public String getValue() {
        return value;
    }

    //Method that sets the  value
    public void setValue(String value) {
        this.value = value;
    }

    // Method that returns the value when converting the object to string
    @Override
    public String toString() {
        return this.value;
    }
}

