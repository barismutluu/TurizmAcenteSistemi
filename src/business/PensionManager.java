package business;

import core.Helper;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.User;

import java.util.ArrayList;


// Class that manages pension operations
public class PensionManager {
    PensionDao pensionDao = new PensionDao();
    HotelManager hotelManager = new HotelManager();

    //Method that retrieves the pension with a specific ID
    public Pension getById(int id) {
        return this.pensionDao.getByID(id);
    }

    // Method to fetch all pensions
    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    //Method that retrieves pensions with a specific hotel ID
    public ArrayList<Pension> getPensionByOtelId(int id) {
        return this.pensionDao.getPensionByOtelId(id);
    }

    // Method that provides the necessary information for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for (Pension obj : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }


    //Method that adds the pension record to the database
    public boolean save(Pension pension) {
        if (pension.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    //Method that updates pension information
    public boolean update(Pension pension) {
        if (this.getById(pension.getId()) == null) {
            Helper.showMsg(pension.getId() + "ID registered pension not found");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    //Method that deletes the pension with a specific ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID registered pension not found");
            return false;
        }
        return this.pensionDao.delete(id);
    }
}
