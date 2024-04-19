package business;

import core.Helper;
import dao.HotelDao;
import dao.UserDao;
import entity.Hotel;
import entity.User;

import javax.management.relation.Role;
import java.util.ArrayList;

public class HotelManager {
    private  final HotelDao hotelDao ;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

//    public User findByLogin(String username , String password){
//        return this.userDao.findByLogin(username,password);
//
//    }
    public ArrayList<Hotel> findAll(){return this.hotelDao.findAll();}

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
            hotelRowList.add(rowObject);

        }
        return hotelRowList;
    }


    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public boolean save(Hotel hotel) {

        return this.hotelDao.save(hotel);

    }

    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.hotelDao.update(hotel);
    }

    public boolean delete(int id) {

        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı marka bulunamadı");
            return false;
        }

        return this.hotelDao.delete(id);


    }

//    public ArrayList<User>  searcForTable(int user_id, Role role){
//        String select ="SELECT * FROM public.user";
//        ArrayList<String> whereList = new ArrayList<>();
//        if (user_id != 0){
//            whereList.add("user_id = " + user_id);
//        }
//        if(role != null ){
//            whereList.add("user_role ='"+role.toString()+"'");
//        }
//
//        String whereStr = String.join(" AND ",whereList);
//        String query=select;
//        if(whereStr.length() > 0){
//            query +=  " WHERE "+whereStr;
//
//        }
//        return this.userDao.selectByQuery(query);
//
//    }
//
//    public ArrayList<User>  searcForTable(int userId){
//        String select ="SELECT * FROM public.user ";
//        ArrayList<String> whereList = new ArrayList<>();
//        if (userId != 0){
//            whereList.add("user_id = " +userId );
//
//        }
//        String whereStr = String.join(" AND ",whereList);
//        String query=select;
//        if(whereStr.length() > 0){
//            query +=  " WHERE "+whereStr;
//
//        }
//
//        return this.userDao.selectByQuery(query);
//
//    }
}
