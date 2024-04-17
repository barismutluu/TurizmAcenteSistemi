package view;

import business.UserManager;
import entity.User;

import javax.management.relation.Role;
import javax.swing.*;

public class NewUserView extends Layout{
    private JPanel container;
    private JTextField fld_user_name;
    private JTextField fld_surname;
   // private JComboBox cmb_user_role;
    private JComboBox<Role> cmb_user_role;
    private JLabel lbl_heading;
    private JTextField fld_name;
    private JTextField fld_pass;
    private JButton btn_user_save;
    private UserManager workerUserManager;


    public NewUserView(User newUserView) {
        this.workerUserManager = new UserManager();
        this.add(container);
        guiInitilaze(300,500);
        //this.cmb_user_role.setModel(new DefaultComboBoxModel<>(Role.roleValueToString());
    }
}
