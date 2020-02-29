package services;

import constants.MariadbConstants;
import constants.State;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    public static void createTheTable() {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "CREATE TABLE IF NOT EXISTS books(\n" +
                    "  id int NOT NULL AUTO_INCREMENT,\n" +
                    "  name varchar(255),\n" +
                    "  description varchar(255),\n" +
                    "  numberOfPages int,\n" +
                    "  state ENUM('TAKEN','NOT_TAKEN'),\n" +
                    "  PRIMARY KEY (id)\n" +
                    ")";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //singleton
    private static BookService bookService = null;

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    //methods

    //creating
    public int createBookByName(Book book) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "INSERT INTO books(name,state) VALUES(?,'NOT_TAKEN')";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //updating
    public int updateBooksName(String name, int id) {
        try(Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS))  {
            String query = "UPDATE books SET name=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateBooksDescription(String description, int id) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "UPDATE books SET description=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateBooksNumberOfPages(int num, int id) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "UPDATE books SET numberOfPages=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, num);
            preparedStatement.setInt(2, id);
           return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateStateToTakenById(int id){
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "UPDATE books SET state='TAKEN' WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateStateToNotTakenById(int id){
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "UPDATE books SET state='NOT_TAKEN' WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //deleting
    public int deleteBookById(int id){
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "DELETE FROM books WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //reading
    public Book getBookById(int id){
        List<Book> books = readingBooks();
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> readingBooks(){
        List<Book> books=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "SELECT * FROM books";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String description=resultSet.getString("description");
                int numOfPages=resultSet.getInt("numberOfPages");
                String state=resultSet.getString("state");
                State st=State.valueOf(state);
                Book book=new Book(id,name,description,numOfPages,st);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> notTakenBooks(){
        List<Book> books=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "SELECT * FROM books WHERE state='NOT_TAKEN'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String description=resultSet.getString("description");
                int numOfPages=resultSet.getInt("numberOfPages");
                String state=resultSet.getString("state");
                State st=State.valueOf(state);
                Book book=new Book(id,name,description,numOfPages,st);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
