package com.happy.dao;

import com.happy.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDao {

    private static Connection conn;
    private static boolean result;

    public boolean saveUser(User user){

        try {

            conn = ConnectionFactory.getConn();
            String query = "insert into user(Username, Email, Password, DateTime) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getDate());

            int executeUpdate = preparedStatement.executeUpdate();

            if(executeUpdate == 1) {
               result = true;
            }else{
                result = false;
            }

        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public User getUser(String email, String password){

        User user = null;

        try {

            conn = ConnectionFactory.getConn();
            String query = "select* from user where Email = ? and Password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getInt("UserId"));
                user.setUsername(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setDate(resultSet.getString("DateTime"));
                user.setProfile(resultSet.getString("Profile"));
            }

        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public boolean updateUser(User user){
        try {

            conn = ConnectionFactory.getConn();
            String query = "update user set Username = ?, Email = ?, Password = ?, Profile = ? where UserId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getProfile());
            preparedStatement.setInt(5, user.getUserId());

            int executeUpdate = preparedStatement.executeUpdate();

            if(executeUpdate == 1) {
               result = true;
            }else{
                result = false;
            }

        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        CommentDao comment = new CommentDao();
        comment.updateUserData(user);

        return result;
    }

    public boolean forgotPassword(User user){
        try {
            conn = ConnectionFactory.getConn();
            String query = "update user set Password = ? where Username = ? AND Email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());

            int executeUpdate = preparedStatement.executeUpdate();

            if(executeUpdate == 1) {
                result = true;
            }else{
                result = false;
            }

        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        CommentDao comment = new CommentDao();
        comment.updateUserData(user);

        return result;
    }
}
