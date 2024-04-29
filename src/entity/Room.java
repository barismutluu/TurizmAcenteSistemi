package entity;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;

import java.util.ArrayList;

public class Room {
    private int id;
    private int hotel_id;
    private int pension_id;
    private int season_id;
    private String type;
    private int stock;
    private double adult_price;
    private double child_price;
    private int bed_capacity;
    private int square_meter;
    private boolean television;
    private boolean minibar;
    private boolean game_console;
    private boolean cash_box;
    private boolean projection;
    private boolean gym;


    public Room() {
    }

    public Room(int hotel_id, int pension_id, int season_id, String type, int stock, double adult_price, double child_price, int bed_capacity, int square_meter, boolean television, boolean minibar, boolean game_console, boolean cash_box, boolean projection) {
        this.hotel_id = hotel_id;
        this.pension_id = pension_id;
        this.season_id = season_id;
        this.type = type;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.bed_capacity = bed_capacity;
        this.square_meter = square_meter;
        this.television = television;
        this.minibar = minibar;
        this.game_console = game_console;
        this.cash_box = cash_box;
        this.projection = projection;
    }


    public Room(int id, int hotel_id, int pension_id, int season_id, String type, int stock, double adult_price, double child_price, int bed_capacity, int square_meter, boolean television, boolean minibar, boolean game_console, boolean cash_box, boolean projection, boolean gym) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.pension_id = pension_id;
        this.season_id = season_id;
        this.type = type;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.bed_capacity = bed_capacity;
        this.square_meter = square_meter;
        this.television = television;
        this.minibar = minibar;
        this.game_console = game_console;
        this.cash_box = cash_box;
        this.projection = projection;
        this.gym = gym;
    }


    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public int setStock(int stock) {
        this.stock = stock;
        return stock;
    }

    public double getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(double adult_price) {
        this.adult_price = adult_price;
    }

    public double getChild_price() {
        return child_price;
    }

    public void setChild_price(double child_price) {
        this.child_price = child_price;
    }

    public int getBed_capacity() {
        return bed_capacity;
    }

    public void setBed_capacity(int bed_capacity) {
        this.bed_capacity = bed_capacity;
    }

    public int getSquare_meter() {
        return square_meter;
    }

    public void setSquare_meter(int square_meter) {
        this.square_meter = square_meter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGame_console() {
        return game_console;
    }

    public void setGame_console(boolean game_console) {
        this.game_console = game_console;
    }

    public boolean isCash_box() {
        return cash_box;
    }

    public void setCash_box(boolean cash_box) {
        this.cash_box = cash_box;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }


    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + id +
                ", hotel_id=" + hotel_id +
                ", pension_id=" + pension_id +
                ", season_id=" + season_id +
                ", room_type='" + type + '\'' +
                ", room_stock=" + stock +
                ", room_adult_price=" + adult_price +
                ", room_child_price=" + child_price +
                ", room_bed_capacity=" + bed_capacity +
                ", room_square_meter=" + square_meter +
                ", room_television=" + television +
                ", room_minibar=" + minibar +
                ", room_game_console=" + game_console +
                ", room_cash_box=" + cash_box +
                ", room_projection=" + projection +
                '}';
    }

    public Hotel getHotel() {
        HotelManager hotelManager = new HotelManager();
        return hotelManager.getById(hotel_id);
    }



    public Pension getPension() {
        PensionManager pensionManager = new PensionManager();
        return pensionManager.getById(this.pension_id);
    }
}
