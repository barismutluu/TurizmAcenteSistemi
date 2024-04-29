package dao;

import core.Db;
import entity.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingDao {
    private final Connection con;

    //the same with the hotelDao, the methods unique to this dao will be explained below.
    // if no explanation is given you can look them up in hoteldao
    public BookingDao() {
        this.con = Db.getInstance();
    }

    // Method to retrieve reservations with a specific hotel ID
    public ArrayList<Booking> getReservationByOtelId(int otelId) {
        ArrayList<Booking> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation WHERE room_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Booking reservation = match(rs);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Method to retrieve a reservation by its ID
    public Booking getByID(int id) {
        Booking obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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

    // Helper method to map ResultSet to Reservation object
    public Booking match(ResultSet rs) throws SQLException {
        Booking obj = new Booking();
        obj.setId(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setGuest_name(rs.getString("guest_name"));
        obj.setGuest_id(rs.getString("guest_id"));
        obj.setGuest_email(rs.getString("guest_email"));
        obj.setGuest_phone(rs.getString("guest_phone"));
        obj.setAdult_count(rs.getInt("adult_count"));
        obj.setChild_count(rs.getInt("child_count"));
        obj.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        obj.setGuest_note(rs.getString("guest_note"));
        obj.setTotal_price(rs.getDouble("total_price"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        return obj;
    }

    // Method to retrieve all reservations
    public ArrayList<Booking> findAll() {
        ArrayList<Booking> resList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {

                resList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    // Method to add a reservation
    public boolean save(Booking reservation) {
        String query = "INSERT INTO public.reservation" +
                "(room_id," +
                "guest_name," +
                "guest_id," +
                "guest_email," +
                "guest_phone," +
                "adult_count," +
                "child_count," +
                "check_in_date," +
                "check_out_date," +
                "guest_note," +
                "total_price," +
                "hotel_id," +
                "pension_id," +
                "season_id)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);

            pr.setInt(1, reservation.getRoom_id());
            pr.setString(2, reservation.getGuest_name());
            pr.setString(3, reservation.getGuest_id());
            pr.setString(4, reservation.getGuest_email());
            pr.setString(5, reservation.getGuest_phone());
            pr.setInt(6, reservation.getAdult_count());
            pr.setInt(7, reservation.getChild_count());
            pr.setDate(8, Date.valueOf(reservation.getCheck_in_date()));
            pr.setDate(9, Date.valueOf(reservation.getCheck_out_date()));
            pr.setString(10, reservation.getGuest_note());
            pr.setDouble(11, reservation.getTotal_price());
            pr.setInt(12, reservation.getHotel_id());
            pr.setInt(13, reservation.getPension_id());
            pr.setInt(14, reservation.getSeason_id());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to delete a reservation
    public boolean delete(int reservation_id) {
        try {
            String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, reservation_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to retrieve reservations by a specific reservation ID
    public ArrayList<Booking> getByListReservationId(int id) {
        return this.selectByQuery("SELECT * FROM public.reservation WHERE id=" + id);
    }

    // Method to retrieve reservations with a specific query
    public ArrayList<Booking> selectByQuery(String query) {
        ArrayList<Booking> resList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                resList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    // Method to update a reservation
    public boolean update(Booking booking) {
        try {
            String query = "UPDATE public.reservation SET " +
                    "room_id = ?," +
                    "guest_name = ?," +
                    "guest_id = ?," +
                    "guest_email = ?," +
                    "guest_phone = ?," +
                    "adult_count = ?," +
                    "child_count = ?," +
                    "check_in_date = ?," +
                    "check_out_date = ?," +
                    "guest_note = ?," +
                    "total_price = ?," +
                    "hotel_id = ?," +
                    "pension_id = ?," +
                    "season_id = ? " +
                    "WHERE reservation_id = ?";

            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, booking.getRoom_id());
            pr.setString(2, booking.getGuest_name());
            pr.setString(3, booking.getGuest_id());
            pr.setString(4, booking.getGuest_email());
            pr.setString(5, booking.getGuest_phone());
            pr.setInt(6, booking.getAdult_count());
            pr.setInt(7, booking.getChild_count());
            pr.setDate(8, Date.valueOf(booking.getCheck_in_date()));
            pr.setDate(9, Date.valueOf(booking.getCheck_out_date()));
            pr.setString(10, booking.getGuest_note());
            pr.setDouble(11, booking.getTotal_price());
            pr.setInt(12, booking.getHotel_id());
            pr.setInt(13, booking.getPension_id());
            pr.setInt(14, booking.getSeason_id());
            pr.setInt(15, booking.getId());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
