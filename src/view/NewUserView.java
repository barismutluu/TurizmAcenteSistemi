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
    // private JComboBox cmb_user_role;
    private JComboBox<User.Role> cmb_user_role;
    private JLabel lbl_heading;
    private JTextField fld_name;
    private JTextField fld_pass;
    private JButton btn_user_save;
    private UserManager workerUserManager;
    private UserManager userManager;
    private User user;

    public NewUserView(User newUserView) {
        this.userManager = new UserManager();
        this.user = user;
        add(container);
        this.guiInitilaze(300, 600);

        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_user_role.setSelectedItem(null);

        if(this.user != null){

            this.fld_user_name.setText(user.getUsername());
            this.fld_name.setText(user.getName());
            this.fld_surname.setText(user.getSurname());
            this.fld_pass.setText(user.getPass());

        }

        this.btn_user_save.addActionListener(e -> {

//            if(Helper.isFieldListEmpty(new JTextField[]{this.fld_user_name,this.fld_name,this.fld_surname,this.fld_pass})){
//                Helper.showMsg("fill");
//            }else{
//                boolean result;
//                ComboItem selectedModel = (ComboItem) this.cmb_user_role.getSelectedItem();
//                this.user.setUsername(this.fld_user_name.getText());
//                this.user.setName(this.fld_name.getText());
//                this.user.setSurname(this.fld_surname.getText());
//                this.user.setPass(this.fld_pass.getText());
//                this.user.setRole((User.Role)this.cmb_user_role.getSelectedItem());
//
//
//                if(this.user.getId() != 0){
//                    result =this.userManager.update(this.user);
//                    //result =this.brandManager.save(new Brand(fld_brand_name.getText()));
//                }else{
//
//                    result=this.userManager.save(this.user);
//                }
//                if (this.userManager.save(user)) {
//                    Helper.showMsg("done");
//                    dispose();
//                } else {
//                    Helper.showMsg("error");
//                }
            if(Helper.isFieldListEmpty(new JTextField[]{this.fld_user_name,this.fld_pass,})){
                Helper.showMsg("fill");
            }else{
                //ComboItem selectedBrand = (ComboItem) cmb_mng_role.getSelectedItem();
                boolean result= false ;
                if (this.user != null){
                    this.user.setUsername(fld_user_name.getText());
                    this.user.setPass(fld_pass.getText());
                    this.user.setRole((User.Role) cmb_user_role.getSelectedItem());
                    result = this.userManager.update(this.user);
                    System.out.println("update kısmına gırdi");
                }else{
                    //result=this.userManager.save(this.user);
                    User obj =new User(fld_user_name.getText(),fld_name.getText(),fld_surname.getText(),fld_pass.getText(), (User.Role) cmb_user_role.getSelectedItem());
                    result =this.userManager.save(obj);
                }
                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }

            }


        });
    }
}