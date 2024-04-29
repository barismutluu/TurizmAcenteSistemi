package core;

import entity.Booking;

import javax.swing.*;
import java.awt.*;
import java.lang.runtime.SwitchBootstraps;
import java.time.LocalDate;
import java.util.ArrayList;


public class Helper {

    public static void setTheme() {
//Method that sets the theme
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    //Method that displays message
    public static void showMsg(String str) {
        optionPayneEN();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Please fill in all fields !";
                title = "Error!";
            }
            case "done" -> {
                msg = "Transaction Successful !";
                title = "conclusion";
            }
            case "notFound" -> {
                msg = "No Records Found !";
                title = "Not found";
            }
            case "error" -> {
                msg = "You made a mistake !";
                title = "Error!";
            }
            default -> {
                msg = str;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        optionPayneEN();
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure you want to do this? !!";

        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure", JOptionPane.YES_NO_OPTION) == 0;

    }

    //Method to check if JTextField is empty or not
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    //Method to check if JTextField is empty or not
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();

    }

    //Method that adjusts the position of the window
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    //Method that sets the language settings
    public static void optionPayneEN() {
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.yesButtonText", "YES");
        UIManager.put("OptionPane.noButtonText", "NO");


    }
}
