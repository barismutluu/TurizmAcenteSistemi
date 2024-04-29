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

    public HotelDao() {
        this.con = Db.getInstance();
    }

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
        String query = "INSERT INTO public.hotel" +
                "(" +
                "hotel_name," +
                "hotel_city," +
                "hotel_area," +
                "hotel_adress," +
                "hotel_eposta, " +
                "hotel_phone, " +
                "hotel_stars, " +
                "hotel_car_park," +
                "hotel_wifi," +
                "hotel_pool," +
                "hotel_fitness," +
                "hotel_concierge," +
                "hotel_spa," +
                "hotel_room_service" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getCity());
            ps.setString(3, hotel.getArea());
            ps.setString(4, hotel.getAdress());
            ps.setString(5, hotel.getEposta());
            ps.setString(6, hotel.getPhone());
            ps.setString(7, hotel.getStars());
            ps.setBoolean(8, hotel.isCar_park());
            ps.setBoolean(9, hotel.isWifi());
            ps.setBoolean(10, hotel.isPool());
            ps.setBoolean(11, hotel.isFitness());
            ps.setBoolean(12, hotel.isConcierge());
            ps.setBoolean(13, hotel.isSpa());
            ps.setBoolean(14, hotel.isRoom_service());

            return ps.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET " +
                "hotel_name = ?," +
                "hotel_city = ?," +
                "hotel_area = ?," +
                "hotel_adress = ?," +
                "hotel_eposta = ?, " +
                "hotel_phone = ?, " +
                "hotel_stars = ?, " +
                "hotel_car_park = ?," +
                "hotel_wifi = ?," +
                "hotel_pool = ?," +
                "hotel_fitness = ?," +
                "hotel_concierge = ?," +
                "hotel_spa = ?," +
                "hotel_room_service = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getCity());
            ps.setString(3, hotel.getArea());
            ps.setString(4, hotel.getAdress());
            ps.setString(5, hotel.getEposta());
            ps.setString(6, hotel.getPhone());
            ps.setString(7, hotel.getStars());
            ps.setBoolean(8, hotel.isCar_park());
            ps.setBoolean(9, hotel.isWifi());
            ps.setBoolean(10, hotel.isPool());
            ps.setBoolean(11, hotel.isFitness());
            ps.setBoolean(12, hotel.isConcierge());
            ps.setBoolean(13, hotel.isSpa());
            ps.setBoolean(14, hotel.isRoom_service());
            ps.setInt(15, hotel.getId());
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
        obj.setCar_park(rs.getBoolean("hotel_car_park"));
        obj.setWifi(rs.getBoolean("hotel_wifi"));
        obj.setPool(rs.getBoolean("hotel_pool"));
        obj.setFitness(rs.getBoolean("hotel_fitness"));
        obj.setConcierge(rs.getBoolean("hotel_concierge"));
        obj.setSpa(rs.getBoolean("hotel_spa"));
        obj.setRoom_service(rs.getBoolean("hotel_room_service"));
        return obj;
    }
}
