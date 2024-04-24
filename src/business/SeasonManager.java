package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

// Class that manages season operations
public class SeasonManager {
    SeasonDao seasonDao = new SeasonDao();
    HotelManager hotelManager = new HotelManager();

    //Method that retrieves the season with a specific ID
    public Season getById(int id) {
        return this.seasonDao.getByID(id);
    }

    //Method that retrieves seasons with a specific hotel ID
    public ArrayList<Season> getSeasonsByOtelId(int id) {
        return this.seasonDao.getSeasonsByOtelId(id);
    }

    // Method to fetch all seasons
    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    // Method that provides the necessary information for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = hotelManager.getById(obj.getHotel_id()).getName();
            rowObject[i++] = obj.getStart_date();
            rowObject[i++] = obj.getFinish_date();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    //Method that adds the season record to the database
    public boolean save(Season season) {
        if (season.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }

    // Method to delete the season with a specific ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID registered season not found");
            return false;
        }
        return this.seasonDao.delete(id);
    }
}