package services;

import constants.MariadbConstants;
import constants.State;
import entities.Customer;
import entities.Password;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    BookService bookService=BookService.getInstance();
    //region singleton
    private static CustomerService customerService=null;
    public static CustomerService getInstance(){
        if(customerService==null){
            customerService=new CustomerService();
        }
        return customerService;
    }
    //endregion

    //region createTable
    public void createTable(){
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "CREATE TABLE IF NOT EXISTS customers(\n" +
                    "  id int NOT NULL AUTO_INCREMENT,\n" +
                    "  username varchar(255),\n" +
                    "  password varchar(255),\n" +
                    "  hasOrder boolean,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ")";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //endregion


    public int takeBook(int customerId,String password,int bookId){
        if(BCrypt.checkpw(password,getCustomerById(customerId).getPassword().getHash())){
            int res=0;
            try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
                String query="UPDATE customers SET hasOrder=true WHERE id=(?)";
                PreparedStatement preparedStatement=con.prepareStatement(query);
                preparedStatement.setInt(1,customerId);
                res+=preparedStatement.executeUpdate();
                String query1="UPDATE books SET state='TAKEN' WHERE id=(?)";
                PreparedStatement preparedStatement1=con.prepareStatement(query1);
                preparedStatement1.setInt(1,bookId);
                res+=preparedStatement1.executeUpdate();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            return res;
        }
        return 0;
    }

    public int returnBook(int customerId,int bookId){
        int res=0;
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="UPDATE customers SET hasOrder=false WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,customerId);
            res+=preparedStatement.executeUpdate();
            String query1="UPDATE books SET state='NOT_TAKEN' WHERE id=(?)";
            PreparedStatement preparedStatement1=con.prepareStatement(query1);
            preparedStatement1.setInt(1,bookId);
            res+=preparedStatement1.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
    }
    //create
    public int createCustomer(Customer customer){
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="INSERT INTO customers(username,password,hasOrder) VALUES (?,?,false)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setString(2,customer.getPassword().getHash());
            return preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    //update

    public int updateCustomersUsername(String username, int id){
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="UPDATE customers SET username=(?) WHERE id= (?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,id);
            return preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateCustomersPassword(int id,String oldPassword,String newPassword){
        Customer customerById = getCustomerById(id);
        int rawsAffected=0;
        if(customerById!=null&&BCrypt.checkpw(oldPassword,customerById.getPassword().getHash())){
            try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
                Password password=new Password(newPassword);
                customerById.setPassword(password);
                String query="UPDATE customers SET password=(?) WHERE id= (?)";
                PreparedStatement preparedStatement=con.prepareStatement(query);
                preparedStatement.setString(1,password.getHash());
                preparedStatement.setInt(2,id);
                rawsAffected=preparedStatement.executeUpdate();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return rawsAffected;
    }

    //delete

    public int deleteCustomer(int id){
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="DELETE FROM customers WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    //read

    public Customer getCustomerById(int id){
        List<Customer> read = read();
        for (Customer customer : read) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    public List<Customer> read(){
        List<Customer> customers=new ArrayList<>();
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="SELECT * FROM customers";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String username=resultSet.getString("username");
                String hash=resultSet.getString("password");
                Customer customer=new Customer(id,username);
                customer.setPasswordsHash(hash);
                customers.add(customer);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return customers;
    }
}
