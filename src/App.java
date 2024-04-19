import business.UserManager;
import core.Helper;
import view.AdminView;
import view.LoginView;
import view.NewUserView;

public class App {
    public static void main(String[] args) {

        //Connection conn = Db.getInstance();
        Helper.setTheme();
        LoginView loginView = new LoginView();
        //   NewUserView newUserView = new NewUserView();
      UserManager userManager  =new UserManager();
      AdminView adminView=new AdminView(userManager.findByLogin("admin","1234"));

    }
}
