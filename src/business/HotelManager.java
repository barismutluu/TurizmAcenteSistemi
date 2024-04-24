package business;

import core.Helper;
import dao.HotelDao;
import dao.UserDao;
import entity.Hotel;
import entity.User;

import javax.management.relation.Role;
import java.util.ArrayList;


// Class that manages hotel operations
public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    // Method to fetch all hotel
    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    // Method that provides the necessary information for the table
    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for (Hotel obj : this.findAll()) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getCity();
            rowObject[i++] = obj.getArea();
            rowObject[i++] = obj.getAdress();
            rowObject[i++] = obj.getEposta();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStars();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_service();
            hotelRowList.add(rowObject);

        }
        return hotelRowList;
    }

    //Method that retrieves the hotel with a specific ID
    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    //Method that adds the hotel record to the database
    public boolean save(Hotel hotel) {

        return this.hotelDao.save(hotel);

    }

    //Method that updates hotel information
    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.hotelDao.update(hotel);
    }

    //Method that deletes the hotel with a specific ID
    public boolean delete(int id) {

        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID registered hotel not found");
            return false;
        }

        return this.hotelDao.delete(id);


    }
}
