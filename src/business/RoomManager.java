package business;

import core.ComboItem;
import core.Helper;
import dao.RoomDao;
import entity.Pension;
import entity.Room;
import entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


// Class that manages room operations
public class RoomManager {
    RoomDao roomDao = new RoomDao();
    HotelManager hotelManager = new HotelManager();

    //Method that retrieves the room with a specific ID
    public Room getById(int id) {
        return this.roomDao.getByID(id);
    }

    // Method to fetch all rooms
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    // Method that provides the necessary information for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getHotel().getCity();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getSquare_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGame_console();
            rowObject[i++] = obj.isCash_box();
            rowObject[i++] = obj.isProjection();
            roomList.add(rowObject);
        }
        return roomList;
    }

    //Method that adds the room save to the database
    public boolean save(Room room) {
        if (room.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    //Method that updates the room stock
    public boolean updateStock(Room room) {
        if (this.getById(room.getId()) == null) {
            return false;
        }
        return this.roomDao.updateStock(room);
    }

    //Method that deletes a room with a specific ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID registered not found");
            return false;
        }
        return this.roomDao.delete(id);
    }

    //Method that updates room information
    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {
            Helper.showMsg(room.getId() + " ID registered not found");
            return false;
        }
        return this.roomDao.update(room);
    }

    //Method that creates a list of room searches
    public ArrayList<Room> searchForRooms(String strt_date, String fnsh_date, String searchCity, String hotelName) {

        return this.roomDao.searchForRooms(strt_date, fnsh_date, searchCity, hotelName);
    }
}



