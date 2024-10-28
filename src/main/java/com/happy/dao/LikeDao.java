package com.happy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDao {

    private static Connection conn;

    public boolean insertLike(int pId, int userId){
        boolean result = false;
        try{
            conn = ConnectionFactory.getConn();
            String query = "INSERT INTO liked(postId, userId) VALUES(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, pId);
            preparedStatement.setInt(2, userId);

            int rowAffect = preparedStatement.executeUpdate();

            if(rowAffect > 0){
                result = true;
            }
        }catch (Exception e){

            e.printStackTrace();

        }finally {

            try {
                if (conn!= null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return result;
    }

    public int countLikeOnPost(int pId){
        int count = 0;

        try{
            conn = ConnectionFactory.getConn();
            String query = "SELECT COUNT(*) FROM liked WHERE postId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }

        }catch (Exception e){

            count = 0;
            e.printStackTrace();

        }finally {

            try {
                if (conn!= null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return count;
    }

    public boolean isUserLikedPost(int pId, int userId){
        boolean isLiked = false;

        try{
            conn = ConnectionFactory.getConn();
            String query = "SELECT COUNT(*) FROM liked WHERE postId = ? AND userId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, pId);
            preparedStatement.setInt(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                isLiked = resultSet.getInt(1) > 0;
            }

        }catch (Exception e){

            e.printStackTrace();

        }finally {

            try {
                if (conn!= null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return isLiked;
    }

    public boolean deleteLike(int pId, int userId){
        boolean deleteLiked = false;
        try{
            conn = ConnectionFactory.getConn();
            String query = "DELETE FROM liked WHERE postId =? AND userId =?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, pId);
            preparedStatement.setInt(2, userId);

            int rowAffect = preparedStatement.executeUpdate();

            if(rowAffect > 0){
                deleteLiked = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (conn!= null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return deleteLiked;
    }
}
