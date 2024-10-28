package com.happy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDeleteDao {
    private Connection con;

    public boolean deleteProfile(int userId){
        boolean result = false;

        PreparedStatement user = null;
        PreparedStatement post = null;
        PreparedStatement liked = null;
        PreparedStatement comment = null;

        try{
            con = ConnectionFactory.getConn();

            String userQuery = "DELETE FROM user WHERE UserId = ?";
            user = con.prepareStatement(userQuery);

            String postQuery = "DELETE FROM post WHERE userId = ?";
            post = con.prepareStatement(postQuery);

            String likedQuery = "DELETE FROM liked WHERE userId = ?";
            liked = con.prepareStatement(likedQuery);

            String commentQuery = "DELETE FROM comment WHERE UserId = ?";
            comment = con.prepareStatement(commentQuery);

            user.setInt(1, userId);
            post.setInt(1, userId);
            liked.setInt(1, userId);
            comment.setInt(1, userId);

            user.executeUpdate();
            post.executeUpdate();
            liked.executeUpdate();
            comment.executeUpdate();

            result = true;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(con!= null) {
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }
}
