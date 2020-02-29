package services;

import constants.MariadbConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderArchiveService {
    private static OrderArchiveService orderArchiveService=null;
    public static OrderArchiveService getInstance(){
        if(orderArchiveService==null){
            orderArchiveService=new OrderArchiveService();
        }
        return orderArchiveService;
    }

    public void createTable(){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="CREATE TABLE IF NOT EXISTS orderArchive(\n" +
                    "    customerId int NOT NULL,\n" +
                    "    bookId int NOT NULL,\n" +
                    "    returnDate varchar(255)\n" +
                    ")";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int addToArchive(int customerId,int bookId,String returnDate){
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="INSERT INTO orderArchive(customerId,bookId,returnDate) VALUES (?,?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setInt(2,bookId);
            preparedStatement.setString(3,returnDate);
            return preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
}
