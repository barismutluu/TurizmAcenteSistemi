package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import javax.management.relation.Role;
import java.util.ArrayList;

public class UserManager {
    private  final UserDao userDao ;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username ,String password){
        return this.userDao.findByLogin(username,password);

    }
    public ArrayList<User> findAll(){return this.userDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<User>userList) {
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

    public ArrayList<User>  searcForTable(User.Role role){
        String query ="SELECT * FROM public.user WHERE user_role = '"+ role.toString()+ "'";
        return this.userDao.selectByQuery(query);

    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public boolean save(User user) {

        return this.userDao.save(user);

    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {

        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı marka bulunamadı");
            return false;
        }

        return this.userDao.delete(id);


    }

    public ArrayList<User>  searcForTable(int user_id, Role role){
        String select ="SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();
        if (user_id != 0){
            whereList.add("user_id = " + user_id);
        }
        if(role != null ){
            whereList.add("user_role ='"+role.toString()+"'");
        }

        String whereStr = String.join(" AND ",whereList);
        String query=select;
        if(whereStr.length() > 0){
            query +=  " WHERE "+whereStr;

        }
        return this.userDao.selectByQuery(query);

    }

    public ArrayList<User>  searcForTable(int userId){
        String select ="SELECT * FROM public.user ";
        ArrayList<String> whereList = new ArrayList<>();
        if (userId != 0){
            whereList.add("user_id = " +userId );

        }
        String whereStr = String.join(" AND ",whereList);
        String query=select;
        if(whereStr.length() > 0){
            query +=  " WHERE "+whereStr;

        }

        return this.userDao.selectByQuery(query);

    }

}
