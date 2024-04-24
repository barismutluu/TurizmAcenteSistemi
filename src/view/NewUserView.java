package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.lang.model.element.Name;
import javax.management.relation.Role;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewUserView extends Layout {
    private JPanel container;
    private JTextField fld_user_name;
    private JTextField fld_surname;
    private JComboBox<User.Role> cmb_user_role;
    private JLabel lbl_heading;
    private JTextField fld_name;
    private JTextField fld_pass;
    private JButton btn_user_save;
    private UserManager workerUserManager;
    private UserManager userManager;
    private User user;

    public NewUserView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        add(container);
        this.guiInitilaze(300, 600);

        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        if (this.user.getId() != 0) {

            this.fld_user_name.setText(user.getUsername());
            this.fld_name.setText(user.getName());
            this.fld_surname.setText(user.getSurname());
            this.fld_pass.setText(user.getPass());
            this.cmb_user_role.setSelectedItem(user.getRole());

        }

        this.btn_user_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_user_name, this.fld_pass,})) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;
                this.user.setUsername(fld_user_name.getText());
                this.user.setName(fld_name.getText());
                this.user.setSurname(fld_surname.getText());
                this.user.setPass(fld_pass.getText());
                this.user.setRole((User.Role) cmb_user_role.getSelectedItem());
                if (user.getId() != 0) {
                    result = userManager.update(user);
                } else {
                    result = userManager.save(user);
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