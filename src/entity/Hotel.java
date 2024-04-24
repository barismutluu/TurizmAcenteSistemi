package entity;

import business.HotelManager;
import core.ComboItem;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String area;
    private String adress;
    private String eposta;
    private String phone;
    private String stars;
    private boolean wifi;
    private boolean car_park;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean room_service;

    public Hotel() {

    }

    public Hotel(String name, String city, String area, String adress, String eposta, String phone, String stars, boolean wifi, boolean car_park, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean room_service) {

        this.name = name;
        this.city = city;
        this.area = area;
        this.adress = adress;
        this.eposta = eposta;
        this.phone = phone;
        this.stars = stars;
        this.wifi = wifi;
        this.car_park = car_park;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.room_service = room_service;
    }

    public Hotel(int id, String name, String city, String area, String adress, String eposta, String phone, String stars, boolean wifi, boolean car_park, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean room_service) {
        HotelManager hotelManager = new HotelManager();

        this.id = id;
        this.name = name;
        this.city = city;
        this.area = area;
        this.adress = adress;
        this.eposta = eposta;
        this.phone = phone;
        this.stars = stars;
        this.wifi = wifi;
        this.car_park = car_park;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.room_service = room_service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isCar_park() {
        return car_park;
    }

    public void setCar_park(boolean car_park) {
        this.car_park = car_park;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoom_service() {
        return room_service;
    }

    public void setRoom_service(boolean room_service) {
        this.room_service = room_service;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getName());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", adress='" + adress + '\'' +
                ", eposta='" + eposta + '\'' +
                ", phone='" + phone + '\'' +
                ", stars='" + stars + '\'' +
                ", wifi=" + wifi + '\'' +
                ", car_park=" + car_park + '\'' +
                ", pool=" + pool + '\'' +
                ", fitness=" + fitness + '\'' +
                ", concierge=" + concierge + '\'' +
                ", spa=" + spa + '\'' +
                ", room_service=" + room_service + '\'' +
                '}';
    }
}
