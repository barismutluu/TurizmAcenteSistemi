import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;
import view.*;

public class App {
    public static void main(String[] args) {

        //Connection conn = Db.getInstance();
        Helper.setTheme();
//        LoginView loginView = new LoginView();
//        //   NewUserView newUserView = new NewUserView();
      UserManager userManager  =new UserManager();
//      AdminView adminView=new AdminView(userManager.findByLogin("admin","1234"));
        EmployeeView employeeView=new EmployeeView(userManager.findByLogin("personel","1234"));
    }
}
