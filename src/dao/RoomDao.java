package dao;

import core.ComboItem;
import core.Db;
import entity.Pension;
import entity.Room;
import entity.Season;
import entity.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;


    public RoomDao() {
        this.con = Db.getInstance();
    }


    public Room getByID(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
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

    public ArrayList<Room> getRoomsByOtelId(int hotelId) {
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Room room = match(rs);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setId(rs.getInt("room_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setType(rs.getString("room_type"));
        obj.setStock(rs.getInt("room_stock"));
        obj.setAdult_price(rs.getDouble("room_adult_price"));
        obj.setChild_price(rs.getDouble("room_child_price"));
        obj.setBed_capacity(rs.getInt("room_bed_capacity"));
        obj.setSquare_meter(rs.getInt("room_square_meter"));
        obj.setTelevision(rs.getBoolean("room_televion"));
        obj.setMinibar(rs.getBoolean("room_minibar"));
        obj.setGame_console(rs.getBoolean("room_game_console"));
        obj.setProjection(rs.getBoolean("room_projection"));
        return obj;
    }


    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {

                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }


    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "room_type," +
                "room_stock," +
                "room_adult_price," +
                "room_child_price," +
                "room_bed_capacity," +
                "room_square_meter," +
                "room_televion," +
                "room_minibar," +
                "room_game_console," +
                "room_cash_box," +
                "room_projection" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }


    // Method to update the stock information of a room
    public boolean updateStock(Room room) {
        String query = "UPDATE public.room SET room_stock = ? WHERE room_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getStock());
            pr.setInt(2, room.getId());

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id) {
        try {
            String query = "DELETE FROM public.room WHERE room_id = ?";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
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


    public boolean update(Room room) {
        try {
            String query = "UPDATE public.room SET " +
                    "hotel_id = ?," +
                    "pension_id = ?," +
                    "season_id= ?," +
                    "room_type= ?," +
                    "room_stock= ?," +
                    "room_adult_price = ?," +
                    "room_child_price = ?," +
                    "room_bed_capacity = ?," +
                    "room_square_meter = ?," +
                    "room_televion = ?," +
                    "room_minibar = ?," +
                    "room_game_console = ?," +
                    "room_cash_box = ?," +
                    "room_projection = ?" +
                    "WHERE room_id = ?";

            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            pr.setInt(15, room.getId());
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Room> searchForRooms(String strt_date, String fnsh_date, String searchCity, String hotelName) {
        ArrayList<Room> searchedRooms = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT r.* FROM room r ");
        queryBuilder.append("INNER JOIN hotel h ON r.hotel_id = h.hotel_id ");
        queryBuilder.append("INNER JOIN hotel_season s ON r.season_id = s.season_id ");
        queryBuilder.append("WHERE 1 = 1 ");

        ArrayList<String> where = new ArrayList<>();

        if (searchCity != null && !searchCity.isEmpty()) {
            where.add("h.hotel_city = '" + searchCity + "'");
        }

        if (hotelName != null && !hotelName.isEmpty()) {
            where.add("h.hotel_name = '" + hotelName + "'");
        }

        if (strt_date != null && !strt_date.isEmpty() && fnsh_date != null && !fnsh_date.isEmpty()) {
            LocalDate startDate = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate endDate = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            where.add("s.start_date <= '" + endDate + "'");
            where.add("s.finish_date >= '" + startDate + "'");
        }

        if (!where.isEmpty()) {
            queryBuilder.append(" AND ");
            queryBuilder.append(String.join(" AND ", where));
        }

        String query = queryBuilder.toString();

        searchedRooms = selectByQuery(query);

        return searchedRooms;
    }
}



