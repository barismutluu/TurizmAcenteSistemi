package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddSeasonView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel_season;
    private JFormattedTextField fld_season_start;
    private JTextField fld_season_finish;
    private JButton btn_save;
    private HotelManager hotelManager;
    private Hotel hotel;
    private SeasonManager seasonManager;
    private Season season;

    public AddSeasonView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.seasonManager = new SeasonManager();
        this.season = new Season();
        this.cmb_hotel_season.getSelectedItem();
        this.add(container);
        this.guiInitilaze(700, 300);


        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel_season.addItem(hotel.getComboItem());
        }
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                ComboItem selectSeason = (ComboItem) cmb_hotel_season.getSelectedItem();
                season.setHotel_id(selectSeason.getKey());
                season.setSeason_type(cmb_hotel_season.getSelectedItem().toString());
                JFormattedTextField[] checkDateList = {fld_season_start, (JFormattedTextField) fld_season_finish};
                if (Helper.isFieldListEmpty(checkDateList)) {
                    Helper.showMsg("fill");
                } else {
                    try {

                        season.setStart_date(LocalDate.parse(fld_season_start.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        season.setFinish_date(LocalDate.parse(fld_season_finish.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                        result = seasonManager.save(season);

                    } catch (DateTimeException ex) {
                        Helper.showMsg("Date Format is Wrong !");
                        return;
                    }
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


    private void createUIComponents() throws ParseException {
        this.fld_season_start = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_season_start.setText("2024-06-01");
        this.fld_season_finish = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_season_finish.setText("2024-12-01");
    }
}