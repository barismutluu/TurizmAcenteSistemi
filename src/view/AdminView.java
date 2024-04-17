package view;

import business.UserManager;
import core.ComboItem;
import entity.*;
import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JTable tbl_user;
    private JComboBox <ComboItem> cmb_role_filter;
    private JButton btn_cncl_role;
    private JButton btn_role_search;
    private UserManager userManager;
    private JPopupMenu UserMenu;
    private JPopupMenu rental_Menu;
    private Object[] col_user;
    private Object[] col_rental;
    private User user;
    private DefaultTableModel tmdl_user = new DefaultTableModel();


    public AdminView(User user) {

        this.userManager = new UserManager();


        add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;
        if (this.user == null) {
            dispose();

        }
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Butona tıklandığında çalışacak kod
                dispose(); // Pencereyi kapat
            }
        });
        this.lbl_welcome.setText("Hoşgeldin : " + this.user.getRole());
        loadUserTable();
        loadUserComponent();

    }
    public void roleFilter() {
        this.cmb_role_filter.removeAllItems();
        for (User obj : userManager.findAll()) {
            this.cmb_role_filter.addItem(new ComboItem(obj.getId(), obj.getRole()));
        }
        this.cmb_role_filter.setSelectedItem(null);
    }

    public void loadUserTable() {
        Object[] col_user = {"Kullanıcı ID", "Kullanıcı Adı", "Adı ", " Soyadı ", "Şifre", " Rolü"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length,this.userManager.findAll());
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
        loadRoleFilter();
//        this.btn_role_search.addActionListener(e -> {
//            if(this.cmb_role_filter.getSelectedItem() != null){
//                ComboItem selectedRole = (ComboItem) this.cmb_role_filter.getSelectedItem();
//                int userId = 0;
//                if (selectedRole != null) {
//                    userId = selectedRole.getKey();
//                }
//                ArrayList<User> userListBySearch = this.userManager.searcForTable();
//           ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_user.length, userListBySearch);
//            }});
//        this.btn_cncl_role.addActionListener(e -> {
//            this.cmb_role_filter.setSelectedItem(null);
//
//        });
    }
    public void loadRoleFilter() {
        this.cmb_role_filter.removeAllItems();
        for (User obj : userManager.findAll()) {
            this.cmb_role_filter.addItem(new ComboItem(obj.getId(),obj.getRole()));
        }
        this.cmb_role_filter.setSelectedItem(null);
    }

    public void loadUserComponent() {

        this.tableRowSelect(this.tbl_user);
        this.UserMenu = new JPopupMenu();
        this.UserMenu.add("Yeni").addActionListener(e -> {
            NewUserView newUserView = new NewUserView(new User());
            newUserView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();

                }
            });

        });
        this.UserMenu.add("Güncelle").addActionListener(e -> {

        });
        this.UserMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadUserTable();
                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_user.setComponentPopupMenu(this.UserMenu);

        this.btn_role_search.addActionListener(e -> {
            if(this.cmb_role_filter.getSelectedItem() != null){
                ComboItem selectedBrand = (ComboItem) this.cmb_role_filter.getSelectedItem();
                int plateId = 0;
                if (selectedBrand != null) {
                    plateId = selectedBrand.getKey();
                }
                ArrayList<User> userListBySearch = this.userManager.searcForTable(plateId);
                ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_rental.length, userListBySearch);
               // loadUserTable(modelRowListBySearch);
            }});
        this.btn_cncl_role.addActionListener(e -> {
            this.cmb_role_filter.setSelectedItem(null);
            loadUserTable();
        });
    }
    }



//    private void loadRentalComponent() {
//        this.tableRowSelect(this.tbl_user);
//
//        this.rental_Menu = new JPopupMenu();
//        this.tbl_user.setComponentPopupMenu(this.rental_Menu);
//
//        this.btn_role_search.addActionListener(e -> {
//            if(this.cmb_role_filter.getSelectedItem() != null){
//                ComboItem selectedBrand = (ComboItem) this.cmb_role_filter.getSelectedItem();
//                int plateId = 0;
//                if (selectedBrand != null) {
//                    plateId = selectedBrand.getKey();
//                }
//                ArrayList<User> userListBySearch = this.userManager.searcForTable(plateId);
//                ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_rental.length, userListBySearch);
//                loadUserTable();
//            }});
//        this.btn_cncl_role.addActionListener(e -> {
//            this.cmb_role_filter.setSelectedItem(null);
//            loadUserTable();
//        });
//    }




