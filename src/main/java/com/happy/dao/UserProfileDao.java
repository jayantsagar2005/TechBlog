package com.happy.dao;

import com.happy.entity.Post;
import com.happy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDao {

    private static Connection conn;

    public User getUser(int userId){
        User user = null;
        try {
            conn = ConnectionFactory.getConn();
            String query = "SELECT* FROM user WHERE UserId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("UserId"));
                user.setUsername(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setDate(resultSet.getString("DateTime"));
                user.setProfile(resultSet.getString("Profile"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn!= null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public List<Post> getUserAllPost(int userId) {
        List<Post> list = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConn();

            String query = "select* from post where userId = ? ORDER BY pId DESC";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int postId = resultSet.getInt("pId");
                String postTitle = resultSet.getString("pTitle");
                String postContent = resultSet.getString("pContent");
                String postCode = resultSet.getString("pCode");
                String postPic = resultSet.getString("pPic");
                String postDateTime = resultSet.getString("pDateTime");
                int postCategoryId = resultSet.getInt("cId");
                int postUserId = resultSet.getInt("userId");

                list.add(new Post(postId, postTitle, postContent, postCode, postPic, postDateTime, postCategoryId, postUserId));

            }

        }catch (Exception e){

            e.printStackTrace();

        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return list;
    }

    public List<Post> getUserPostByCategoryId(int categoryId, int userId){
        List<Post> list = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConn();

            String query = "select* from post where cId = ? AND userId = ? ORDER BY pId DESC";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int postId = resultSet.getInt("pId");
                String postTitle = resultSet.getString("pTitle");
                String postContent = resultSet.getString("pContent");
                String postCode = resultSet.getString("pCode");
                String postPic = resultSet.getString("pPic");
                String postDateTime = resultSet.getString("pDateTime");
                int postCategoryId = resultSet.getInt("cId");
                int postUserId = resultSet.getInt("userId");

                list.add(new Post(postId, postTitle, postContent, postCode, postPic, postDateTime, postCategoryId, postUserId));

            }

        }catch (Exception e){

            e.printStackTrace();

        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return list;
    }
}
