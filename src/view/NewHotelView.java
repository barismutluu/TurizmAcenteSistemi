package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;

public class NewHotelView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JTextField fld_area;
    private JTextField fld_adress;
    private JTextField fld_email;
    private JTextField fld_phone;
    private JTextField fld_stars;
    private JButton btn_save;
    private JPanel pnl_left;
    private JRadioButton rbut_carpark;
    private JRadioButton rbut_wifi;
    private JRadioButton rbut_swim;
    private JRadioButton rbut_gym;
    private JRadioButton rbut_concierge;
    private JRadioButton rbut_spa;
    private JRadioButton rbut_roomservices;
    private HotelManager hotelManager;
    private Hotel hotel;

    public NewHotelView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        add(container);
        this.guiInitilaze(400, 700);


        if (this.hotel.getId() != 0) {

            this.fld_hotel_name.setText(hotel.getName());
            this.fld_city.setText(hotel.getCity());
            this.fld_area.setText(hotel.getArea());
            this.fld_adress.setText(hotel.getAdress());
            this.fld_email.setText(hotel.getEposta());
            this.fld_phone.setText(hotel.getPhone());
            this.fld_stars.setText(hotel.getStars());
            this.rbut_carpark.setSelected(hotel.isCar_park());
            this.rbut_wifi.setSelected(hotel.isWifi());
            this.rbut_swim.setSelected(hotel.isPool());
            this.rbut_gym.setSelected(hotel.isFitness());
            this.rbut_concierge.setSelected(hotel.isConcierge());
            this.rbut_spa.setSelected(hotel.isSpa());
            this.rbut_roomservices.setSelected(hotel.isRoom_service());

        }
        btn_save.addActionListener(e -> {

            JTextField[] checkFieldList = {this.fld_hotel_name, this.fld_city, this.fld_area, this.fld_adress, this.fld_email, this.fld_phone, this.fld_stars};

            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");

            } else {
                boolean result = false;
                this.hotel.setName(this.fld_hotel_name.getText());
                this.hotel.setCity(this.fld_city.getText());
                this.hotel.setArea(this.fld_area.getText());
                this.hotel.setAdress(this.fld_adress.getText());
                this.hotel.setEposta(this.fld_email.getText());
                this.hotel.setPhone(this.fld_phone.getText());
                this.hotel.setStars(this.fld_stars.getText());
                this.hotel.setCar_park(this.rbut_carpark.isSelected());
                this.hotel.setWifi(this.rbut_wifi.isSelected());
                this.hotel.setPool(this.rbut_swim.isSelected());
                this.hotel.setFitness(this.rbut_gym.isSelected());
                this.hotel.setConcierge(this.rbut_concierge.isSelected());
                this.hotel.setSpa(this.rbut_spa.isSelected());
                this.hotel.setRoom_service(this.rbut_roomservices.isSelected());


                if (hotel.getId() != 0) {
                    result = hotelManager.update(hotel);
                } else {
                    result = hotelManager.save(hotel);
                }

                if (result) {
                    Helper.showMsg("done");
                    dispose();

                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }
}
