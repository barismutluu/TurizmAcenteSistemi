package dao;

import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao(){
        this.con = Db.getInstance();
    }

//    public User findByLogin (String username , String password){
//        User obj = null;
//        String query ="SELECT * FROM public.user WHERE  user_name = ? AND  user_pass = ? ";
//        try {
//            PreparedStatement pr = this.con.prepareStatement(query);
//            pr.setString(1,username);
//            pr.setString(2,password);
//            ResultSet rs = pr.executeQuery();
//            if(rs.next()){
//                obj= this.match(rs);
//
//
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return obj;
//
//
//    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
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

    public ArrayList<Hotel> findAll() {

        return this.selectByQuery("SELECT * FROM public.hotel ORDER BY hotel_id ASC");
    }


    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_id," +
                "hotel_name,"+
                "hotel_city," +
                " hotel_area," +
                " hotel_adress,"+
                " hotel_eposta " +
                " hotel_phone " +
                " hotel_stars " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, hotel.getId());
            ps.setString(2, hotel.getName());
            ps.setString(3, hotel.getCity());
            ps.setString(4, hotel.getArea());
            ps.setString(5, hotel.getAdress());
            ps.setString(6, hotel.getEposta());
            ps.setString(7, hotel.getPhone());
            ps.setString(8, hotel.getStars());
            ps.setInt(9, hotel.getId());
            return ps.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_id," +
                "hotel_name,"+
                "hotel_city," +
                " hotel_area," +
                " hotel_adress,"+
                " hotel_eposta " +
                " hotel_phone " +
                " hotel_stars " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, hotel.getId());
            ps.setString(2, hotel.getName());
            ps.setString(3, hotel.getCity());
            ps.setString(4, hotel.getArea());
            ps.setString(5, hotel.getAdress());
            ps.setString(6, hotel.getEposta());
            ps.setString(7, hotel.getPhone());
            ps.setString(8, hotel.getStars());
            ps.setInt(9, hotel.getId());
            return ps.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public ArrayList<Hotel> selectByQuery(String query) {
        // System.out.println(query);
        ArrayList<Hotel> hotelList = new ArrayList<>();

        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelList;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setCity(rs.getString("hotel_city"));
        obj.setArea(rs.getString("hotel_area"));
        obj.setAdress(rs.getString("hotel_adress"));
        obj.setEposta(rs.getString("hotel_eposta"));
        obj.setPhone(rs.getString("hotel_phone"));
        obj.setStars(rs.getString("hotel_stars"));
        return obj;
    }
}
