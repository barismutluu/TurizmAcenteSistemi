package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import javax.management.relation.Role;
import java.util.ArrayList;

// Class that manages user operations
public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    //method for matching information on the login screen
    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);

    }

    // Method to fetch all users
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    // Method that provides the necessary information for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User obj : userList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getSurname();
            rowObject[i++] = obj.getPass();
            rowObject[i++] = obj.getRole();
            userRowList.add(rowObject);

        }
        return userRowList;
    }

    public ArrayList<User> searcForTable(User.Role role) {
        String query = "SELECT * FROM public.user WHERE user_role = '" + role.toString() + "'";
        return this.userDao.selectByQuery(query);

    }

    //Method that retrieves the user with a specific ID
    public User getById(int id) {
        return this.userDao.getById(id);
    }

    //Method that adds the user save to the database
    public boolean save(User user) {

        return this.userDao.save(user);

    }

    //Method that updates user information
    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }

    //Method that deletes a user with a specific ID
    public boolean delete(int id) {

        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID registered not found");
            return false;
        }

        return this.userDao.delete(id);


    }

}
