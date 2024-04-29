package entity;

public class User {

    private int id;
    private String username;
    private String name;
    private String surname;
    private String pass;
    private Role role;


    public User() {

    }

    public enum Role {
        ADMIN,
        EMPLOYEE
    }

    public User(String username, String name, String surname, String pass, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.role = role;
    }

    public User(int id, String username, String name, String surname, String pass, Role role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + pass + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
