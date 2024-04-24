package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.User;

import javax.swing.*;

public class AddPensionView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_persion;
    private JButton btn_save;
    private PensionManager pensionManager;
    private Pension pension;
    private HotelManager hotelManager;
    private Hotel hotel;
    private User user;


    public AddPensionView(int hotel_id) {

        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.pension = new Pension();
        this.add(container);
        this.guiInitilaze(375, 250);
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel_name.addItem(hotel.getComboItem());

        }

        this.btn_save.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedHotel = (ComboItem) cmb_hotel_name.getSelectedItem();
            this.pension.setHotel_id(selectedHotel.getKey());
            this.pension.setPension_type(cmb_persion.getSelectedItem().toString());

            if (this.pension.getId() != 0) {
                result = this.pensionManager.update(this.pension);

            } else {
                result = this.pensionManager.save(this.pension);
            }
            if (result) {
                Helper.showMsg("done");
                dispose();
            } else {
                Helper.showMsg("error");
            }
        });
    }

}


