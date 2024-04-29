package view;

import business.UserManager;
import entity.User;
import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JTable tbl_user;
    private JComboBox<User.Role> cmb_role_filter;
    private JButton btn_cncl_role;
    private JButton btn_role_search;
    private UserManager userManager;
    private JPopupMenu UserMenu;
    private JPopupMenu rental_Menu;
    private Object[] col_user;
    private Object[] col_rental;
    private User user;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JTable tbl_room = new JTable();

    public JTable getTbl_room() {
        return tbl_room;
    }

    public void setTbl_room(JTable tbl_room) {
        this.tbl_room = tbl_room;
    }


    public AdminView(User user) {

        this.userManager = new UserManager();


        add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;
        if (this.user == null) {
            dispose();

        }

        this.lbl_welcome.setText("Welcome : " + this.user.getRole());
        this.cmb_role_filter.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_role_filter.setSelectedItem(null);
        loadUserTable(null);
        loadUserComponent();

        tableRowSelect(tbl_room);
    }

    public void loadUserTable(ArrayList<Object[]> userList) {

        this.col_user = new Object[]{"User ID", "User Name", "Name ", " Surname ", "Password", " Role"};
        if (userList == null) {
            userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());
        }
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }

    public void loadUserTableu() {
        col_user = new Object[]{"User ID", "User Name", "Name ", " Surname ", "Password", " Role"};
        ArrayList<User> userList = this.userManager.findAll();
        ArrayList<Object[]> userObjects = this.userManager.getForTable(col_user.length, userList);
        createTable(this.tmdl_user, this.tbl_user, col_user, userObjects);
    }

    @Override
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }


    public void loadUserComponent() {

        btn_logout.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

        this.tableRowSelect(this.tbl_user);
        this.UserMenu = new JPopupMenu();
        this.UserMenu.add("New").addActionListener(e -> {
            NewUserView newUserView = new NewUserView(new User());
            newUserView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);

                }
            });

        });
        this.UserMenu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user, 0);
            NewUserView brandView = new NewUserView(this.userManager.getById(selectUserId));
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTableu();

                }
            });
        });

        this.UserMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_user.setComponentPopupMenu(this.UserMenu);

        this.btn_role_search.addActionListener(e -> {

            if (this.cmb_role_filter.getSelectedItem() != null) {
                ArrayList<User> rolListBySearch = this.userManager.searcForTable((User.Role) cmb_role_filter.getSelectedItem());
                ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_user.length, rolListBySearch);
                loadUserTable(modelRowListBySearch);
            }
        });
        this.btn_cncl_role.addActionListener(e -> {
            this.cmb_role_filter.setSelectedItem(null);
            loadUserTable(null);
        });
    }
}




