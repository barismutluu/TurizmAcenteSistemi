package entity;

import core.ComboItem;

public class Pension {
    private int id;
    private String pension_type;
    private int hotel_id;

    public Pension() {
    }

    public Pension(int id, String pension_type, int hotel_id) {
        this.id = id;
        this.pension_type = pension_type;
        this.hotel_id = hotel_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), "Pension ID : " + this.getId() + " Hotel ID : " + this.getHotel_id() + " Pension Type : " + this.getPension_type());
    }

    @Override
    public String toString() {
        return "Pension{" +
                "pension_id=" + id +
                ", pension_type='" + pension_type + '\'' +
                ", hotel_id=" + hotel_id +
                '}';
    }
}
