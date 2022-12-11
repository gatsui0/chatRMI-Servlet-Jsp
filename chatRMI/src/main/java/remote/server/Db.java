package remote.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connectionDataBase() throws ClassNotFoundException {
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/";
            // Database name to access
            String dbName = "chat_rmi";
            String dbUsername = "root";
            String dbPassword = "root";

            Class.forName(dbDriver);
            this.connection = DriverManager.getConnection(
                    dbURL + dbName, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public boolean Login(String login, String password){
        try {
            preparedStatement = connection.prepareStatement(
                "select * from users where login = ? and password = ?");
            preparedStatement.setString(1, login);  
            preparedStatement.setString(2, password);  
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean existUser(String login){
        try {
            preparedStatement = connection.prepareStatement(
                "select * from users where login = ?");
            preparedStatement.setString(1, login);  
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if(resultSet.getString("login") == null) return false;
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // private User writeResultSet(ResultSet resultSet) throws SQLException {
    //     int id = resultSet.getInt("id");
    //     String name = resultSet.getString("name");
    //     String login = resultSet.getString("login");
    //     String password = resultSet.getString("password");
    //     User user = new User(id, name, login, password);
    //     return user;
    // }

    public void createUser(String name, String login, String password) {

        try {

            preparedStatement = connection.prepareStatement(
                    "insert into users(name, login, password) values( ? , ? , ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean login(String login, String password) {
        try {
            preparedStatement = connection.prepareStatement(
                "select * from users where login = ? and password = ?");
            preparedStatement.setString(1, login);  
            preparedStatement.setString(2, password);  
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if(!resultSet.getString(3).isEmpty()) return true;


        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
