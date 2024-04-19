package view;

import business.HotelManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class HotelView extends Layout {
    private JPanel container;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTable tbl_hotel;
    private JPanel pnl_top;
    private HotelManager hotelManager;
    private Hotel hotel;
    private JPopupMenu HotelMenu;
    private Object[] col_hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();

    public HotelView(Hotel hotel) {

        this.hotelManager = new HotelManager();


        add(container);
        this.guiInitilaze(1000, 500);
        this.hotel = hotel;
        if (this.hotel == null) {
            dispose();

        }
        btn_logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Butona tıklandığında çalışacak kod
                dispose(); // Pencereyi kapat
            }
        });
        this.lbl_welcome.setText("Hoşgeldin : " + this.hotel.getName());
        loadHotelTable();
        loadHotelComponent();


    }

    public void loadHotelTable() {
        Object[] col_hotel_list = {"Otel ID", "Otel Adı", "Şehir ", " Bölge ", "Adres", " EPosta", "Telefon", "Yıldız"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel_list.length);
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel_list, hotelList);
    }

    public void loadHotelComponent() {

        this.tableRowSelect(this.tbl_hotel);
        this.HotelMenu = new JPopupMenu();
        this.HotelMenu.add("Yeni").addActionListener(e -> {
//            NewUserView newUserView = new NewUserView(new User());
//            newUserView.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    loadHotelTable(null);
//
//                }
//            });

        });
        this.HotelMenu.add("Güncelle").addActionListener(e -> {
//            int selectHotelId   = this.getTableSelectedRow(tbl_hotel,0);
//            NewUserView brandView = new NewUserView(this.hotelManager.getById(selectBrandId));
//            brandView.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    loadHotelTable(null);
////                    loadModelFilterBrand();
////                    loadCarTable();
////                    loadRentalFilterPlate();
//                }
//            });
        });

        this.HotelMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_hotel.setComponentPopupMenu(this.HotelMenu);
    }
}
