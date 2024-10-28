package com.happy.dao;

import com.happy.entity.Comment;
import com.happy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDao {
    private static Connection conn;

    public boolean insertComment(Comment comment){
        boolean result = false;

        try{
            conn = ConnectionFactory.getConn();
            String query = "INSERT INTO comment(PostId, Username, UserPic, Comment, DateTime, UserId) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, comment.getPostId());
            preparedStatement.setString(2, comment.getUsername());
            preparedStatement.setString(3, comment.getUserPic());
            preparedStatement.setString(4, comment.getComment());
            preparedStatement.setString(5, comment.getDateTime());
            preparedStatement.setInt(6, comment.getUserId());

            result = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<Comment> getComment(int postId){
        ArrayList<Comment> list = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConn();
            String query = "SELECT* FROM comment WHERE PostId=? ORDER BY CommentId DESC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                list.add(new Comment(resultSet.getInt("CommentId"), resultSet.getInt("PostId"), resultSet.getString("Username"), resultSet.getString("UserPic"), resultSet.getString("Comment"), resultSet.getString("DateTime"), resultSet.getInt("UserId")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return list;
    }

    public int countCommentOnPost(int postId){
        int count = 0;

        try{
            conn = ConnectionFactory.getConn();
            String query = "SELECT COUNT(*) FROM comment WHERE PostId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return count;
    }

    public void updateUserData(User user){
        try{
            conn = ConnectionFactory.getConn();
            String query = "UPDATE comment SET Username = ?, UserPic = ? WHERE UserId = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getProfile());
            preparedStatement.setInt(3, user.getUserId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
