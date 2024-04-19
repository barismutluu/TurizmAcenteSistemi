package dao;

import core.Db;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private final  Connection con;

    public UserDao(){
        this.con = Db.getInstance();
    }
//    public ArrayList <User>  findAll(){
//        ArrayList<User> userList = new ArrayList<>();
//        String sql ="SELECT * FROM public.user";
//        try {
//            //ResultSet rs =this.con.createStatement().executeQuery(sql);
//            Statement st =this.con.createStatement();
//            ResultSet rs =st.executeQuery(sql);
//
//            while(rs.next()){
//                userList.add(this.match(rs));
//
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return userList;
//    }
    public User findByLogin (String username ,String password){
        User obj = null;
        String query ="SELECT * FROM public.user WHERE  user_name = ? AND  user_pass = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj= this.match(rs);


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;


    }
//    public User  match(ResultSet rs) throws SQLException {
//        User obj   = new User();
//        obj.setId(rs.getInt("user_id"));
//        obj.setUsername(rs.getString("user_name"));
//        obj.setUsername(rs.getString("user_pass"));
//        obj.setRole(rs.getString("user_role"));
//        return obj;
//    }

    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }

    public ArrayList<User> findAll() {

        return this.selectByQuery("SELECT * FROM public.user ORDER BY user_id ASC");
    }


    public boolean save(User user) {
        String query = "INSERT INTO public.user " +
                "(" +
                "user_name,"+
                " name," +
                " user_surname," +
                " user_pass,"+
                " user_role" +
                ")" +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getPass());
            ps.setString(5, user.getRole().toString());
            return ps.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }

    public boolean update(User user) {
        String query = "INSERT INTO public.user " +
                "(" +
                "user_id," +
                "user_name, name," +
                " user_surname," +
                " user_pass, user_role) " +
                ")" +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPass());
            ps.setString(6, user.getRole().toString());
            ps.setInt(7, user.getId());
            return ps.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE user_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public ArrayList<User> selectByQuery(String query) {
        // System.out.println(query);
        ArrayList<User> userList = new ArrayList<>();

        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setName(rs.getString("name"));
        obj.setSurname(rs.getString("user_surname"));
        obj.setPass(rs.getString("user_pass"));
        obj.setRole(User.Role.valueOf(rs.getString("user_role")));
        return obj;
    }

}
