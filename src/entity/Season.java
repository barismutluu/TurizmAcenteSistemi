package entity;

import core.ComboItem;

import java.time.LocalDate;

public class Season {
    private int id;
    private int hotel_id;
    private LocalDate start_date;
    private LocalDate finish_date;
    private String season_type;

    public Season(int id, int hotel_id, String start_date, String finish_date, String season_type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.start_date = LocalDate.parse(start_date);
        this.finish_date = LocalDate.parse(finish_date);
        this.season_type = season_type;
    }

    public String getSeason_type() {
        return season_type;
    }

    public void setSeason_type(String season_type) {
        this.season_type = season_type;
    }

    public Season() {
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), "( Start Date: -> " + this.getStart_date() + " - " + " Finish Date: -> " + this.getFinish_date() + ")");
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", start_date='" + start_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                '}';
    }

}