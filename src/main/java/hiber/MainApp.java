package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car bmw = new Car(1555, "BMW");
      Car volvo = new Car(4785, "Volvo");
      Car mercedes = new Car(7564, "Mercedes");

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", bmw));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", volvo));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", mercedes));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println(userService.getUserByCar(1555, "BMW"));

      context.close();
   }
}
