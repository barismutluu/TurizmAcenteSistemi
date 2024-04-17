package core;

import javax.swing.*;
import java.awt.*;
import java.lang.runtime.SwitchBootstraps;


public class Helper {

    public static void  setTheme() {

        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }
    public static void showMsg(String str){
        optionPayneTR();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata!";
            }
            case "done" -> {
                msg = "İşlem Başarılı !";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg ="Kayıt bulunamadı !";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı işlem yaptınız !";
                title = "Hata!";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public  static  boolean confirm(String str){
        optionPayneTR();
        String msg;
        if(str.equals("sure")){
            msg="Bu işlemi yapmak istediginize eminmisiniz !!";

        }else{
            msg=str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Eminmisiniz",JOptionPane.YES_NO_OPTION)==0;

    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for (JTextField field :fieldList){
            if(isFieldEmpty(field)) return true;
        }
        return false;
    }
    public  static  boolean  isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();

    }
    public static  int getLocationPoint(String type, Dimension size){
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static void optionPayneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");


    }

}
