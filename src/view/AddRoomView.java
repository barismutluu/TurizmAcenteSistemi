package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRoomView extends Layout {
    private JPanel container;
    private JButton btn_save;
    private JRadioButton rbut_television;
    private JRadioButton rbut_minibar;
    private JRadioButton rbut_cashbox;
    private JRadioButton rbut_projection;
    private JRadioButton rbut_game_console;
    private JComboBox cmb_room_add_hotel;
    private JComboBox cmb_pension_add;
    private JComboBox cmb_season_add;
    private JComboBox cmb_room_type_add;
    private JTextField fld_stock;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_bed_capacity;
    private JTextField fld_square_meter;

    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private ComboItem comboItem;
    private Hotel hotel;
    private Room room;
    private Season season;
    private RoomManager roomManager;

    public AddRoomView() {
        this.add(container);
        this.guiInitilaze(725, 425);
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();

        for (Hotel hotel : hotelManager.findAll()) {
            this.cmb_room_add_hotel.addItem(hotel.getComboItem());
        }


        cmb_room_add_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedHotelId = ((ComboItem) cmb_room_add_hotel.getSelectedItem()).getKey();

                if (selectedHotelId != 0) {
                    cmb_pension_add.removeAllItems();
                    for (Pension pension : pensionManager.getPensionByOtelId(selectedHotelId)) {
                        cmb_pension_add.addItem(pension.getComboItem());
                        System.out.println("pensions: " + pension);
                    }
                }

                ArrayList<Season> seasons = seasonManager.getSeasonsByOtelId(((ComboItem) cmb_room_add_hotel.getSelectedItem()).getKey());
                System.out.println("seasons: " + seasons);
                cmb_season_add.removeAllItems();
                for (Season season : seasons) {
                    cmb_season_add.addItem(season.getComboItem());

                }

            }
        });
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField[] selectedRoomList = new JTextField[]{fld_adult_price, fld_child_price, fld_bed_capacity, fld_bed_capacity, fld_square_meter};

                if (Helper.isFieldListEmpty(selectedRoomList)) {
                    Helper.showMsg("fill");
                } else {
                    String stockText = fld_stock.getText();
                    boolean result = false;
                    ComboItem selectedHotel = (ComboItem) cmb_room_add_hotel.getSelectedItem();
                    ComboItem selectedPension = (ComboItem) cmb_pension_add.getSelectedItem();
                    ComboItem selectedSeason = (ComboItem) cmb_season_add.getSelectedItem();
                    room.setSeason_id(selectedSeason.getKey());
                    room.setPension_id(selectedPension.getKey());
                    room.setHotel_id(selectedHotel.getKey());
                    room.setType(String.valueOf(cmb_room_type_add.getSelectedItem()));
                    room.setStock(Integer.parseInt(stockText));
                    room.setAdult_price(Double.parseDouble(fld_adult_price.getText()));
                    room.setChild_price(Double.parseDouble(fld_child_price.getText()));
                    room.setBed_capacity(Integer.parseInt(fld_bed_capacity.getText()));
                    room.setSquare_meter(Integer.parseInt(fld_square_meter.getText()));
                    room.setTelevision(rbut_television.isSelected());
                    room.setMinibar(rbut_minibar.isSelected());
                    room.setGame_console(rbut_game_console.isSelected());
                    room.setCash_box(rbut_cashbox.isSelected());
                    room.setProjection(rbut_projection.isSelected());

                    if (room.getId() != 0) {
                        result = roomManager.update(room);
                    } else {
                        result = roomManager.save(room);
                    }
                    if (result) {
                        Helper.showMsg("done");

                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }
                }

            }
        });
    }

}
