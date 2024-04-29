package business;

import core.Helper;
import dao.BookingDao;
import dao.HotelDao;
import dao.RoomDao;
import entity.Booking;
import entity.Room;

import java.util.ArrayList;

// Class that manages Booking operations
public class BookingManager {

    private RoomManager roomManager;
    private Room room;
    private RoomDao roomDao;
    private Booking booking;
    private BookingDao bookingDao;
    private SeasonManager seasonManager;

    public BookingManager() {
        this.bookingDao = new BookingDao();
        this.room = new Room();
        this.booking = new Booking();
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
    }


    // Retrieves a reservation by a specific ID
    public Booking getById(int id) {
        return this.bookingDao.getByID(id);
    }

    // Retrieves reservations by a specific hotel ID
    public ArrayList<Booking> getReservationByOtelId(int id) {
        return this.bookingDao.getReservationByOtelId(id);
    }

    // Retrieves all reservations
    public ArrayList<Booking> findAll() {
        return this.bookingDao.findAll();
    }


    // Provides information necessary for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Booking> roomList) {
        ArrayList<Object[]> resList = new ArrayList<>();
        for (Booking obj : roomList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getGuest_name();
            rowObject[i++] = obj.getGuest_id();
            rowObject[i++] = obj.getGuest_email();
            rowObject[i++] = obj.getGuest_phone();
            rowObject[i++] = obj.getAdult_count();
            rowObject[i++] = obj.getChild_count();
            rowObject[i++] = obj.getCheck_in_date();
            rowObject[i++] = obj.getCheck_out_date();
            rowObject[i++] = obj.getGuest_note();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            resList.add(rowObject);
        }
        return resList;
    }

    // Adds a reservation record to the database
    public boolean save(Booking reservation) {
        if (reservation.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.bookingDao.save(reservation);
    }

    // Deletes a reservation by a specific ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(" Reservation Not found");
            return false;
        }
        return this.bookingDao.delete(id);
    }

    // Updates reservation information
    public boolean update(Booking reservation) {
        if (this.getById(reservation.getId()) == null) {
            Helper.showMsg("Reservation " + reservation.getId() + " not found");
            return false;
        }
        return this.bookingDao.update(reservation);
    }
}