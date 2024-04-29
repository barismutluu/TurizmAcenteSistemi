package entity;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int room_id;
    private String guest_name;
    private String guest_id;
    public String guest_email;
    public String guest_phone;
    private int adult_count;
    private int child_count;
    public LocalDate check_in_date;
    public LocalDate check_out_date;
    private String guest_note;
    private double total_price;
    private int hotel_id;
    private int pension_id;
    private int season_id;

    public Booking() {
    }

    public Booking(int id, int room_id, String guest_name, String guest_id, String guest_mail, String guest_phone, int adult_count, int child_count, LocalDate check_in_date, LocalDate check_out_date, String guest_note, double total_price) {
        this.id = id;
        this.room_id = room_id;
        this.guest_name = guest_name;
        this.guest_id = guest_id;
        this.guest_email = guest_mail;
        this.guest_phone = guest_phone;
        this.adult_count = adult_count;
        this.child_count = child_count;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.guest_note = guest_note;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(String guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }


    public int getChild_count() {
        return child_count;
    }

    public void setChild_count(int child_count) {
        this.child_count = child_count;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getGuest_note() {
        return guest_note;
    }

    public void setGuest_note(String guest_note) {
        this.guest_note = guest_note;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", guest_name='" + guest_name + '\'' +
                ", guest_id='" + guest_id + '\'' +
                ", guest_mail='" + guest_email + '\'' +
                ", guess_phone='" + guest_phone + '\'' +
                ", adult_count=" + adult_count +
                ", child_count=" + child_count +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", guest_note='" + guest_note + '\'' +
                ", total_price=" + total_price +
                '}';
    }
}