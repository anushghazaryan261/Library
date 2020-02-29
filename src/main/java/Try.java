import constants.State;
import controller.BookController;
import controller.CustomerController;
import entities.Customer;
import entities.Password;
import org.mindrot.jbcrypt.BCrypt;
import services.BookService;
import services.CustomerService;

import java.util.List;

public class Try {
    public static void main(String[] args) {

        CustomerController customerController=CustomerController.getInstance();
        customerController.createCustomer();


    }
}
