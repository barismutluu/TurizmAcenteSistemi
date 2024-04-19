package view;

import business.HotelManager;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JTabbedPane tabbedPane1;
    private JButton btn_logout;
    private JTable tbl_hotels;
    private JButton btn_update;
    private JButton btn_delete;
    private JButton btn_add;
    private JLabel lbl_employee;
    private JLabel lbl_welcome;
    private JLabel lbl_update;
    private JLabel lbl_delete;
    private JLabel lbl_add;
    private User user;
    private DefaultTableModel tmdl_hotels = new DefaultTableModel();
    private HotelManager hotelManager;



    public EmployeeView(User user) {

        this.hotelManager=new HotelManager();

        add(container);
        this.guiInitilaze(1000, 500);

        this.user = user;
        if (this.user == null) {
            dispose();
        }
        this.lbl_welcome.setText("Welcome : " + this.user.getName());

       loadHotelComponent();
        loadHotelTable();
    }
    public void loadHotelTable() {
        Object[] col_hotel_list = {"Otel ID", "Otel Adı", "Şehir ", " Bölge ", "Adres", " EPosta", "Telefon", "Yıldız"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel_list.length);
        this.createTable(this.tmdl_hotels, this.tbl_hotels, col_hotel_list, hotelList);
    }

    public void loadHotelComponent() {
        btn_logout.addActionListener(e -> {
           LoginView loginView=new LoginView();
            dispose();
        });
    }

}
