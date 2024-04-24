package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        add(container);
        this.guiInitilaze(400, 400);


        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_username)) {
                Helper.showMsg("Enter Username");
            } else if (Helper.isFieldEmpty(fld_pass)) {
                Helper.showMsg("Enter Password");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_pass.getText());
                if (loginUser == null) {
                    Helper.showMsg("User not found");
                } else {
                    if (loginUser.getRole().toString().equals("Admin") || loginUser.getRole().toString().equals("ADMIN") || loginUser.getRole().toString().equals("admin")) {
                        AdminView adminView = new AdminView(loginUser);
                        adminView.setVisible(true);
                        dispose();
                    } else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        employeeView.setVisible(true);
                        dispose();
                    }
                }
            }
        });
    }

}
