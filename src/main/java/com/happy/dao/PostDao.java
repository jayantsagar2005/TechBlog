package com.happy.dao;

import com.happy.entity.Category;
import com.happy.entity.Post;
import com.happy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    private static Connection conn;
    private boolean result;

    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> list = new ArrayList<>();

        try{

            conn = ConnectionFactory.getConn();
            String query = "select* from categories";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int Id = resultSet.getInt("cid");
                String Title = resultSet.getString("cname");
                String Description = resultSet.getString("cdescription");
                String cFileName = resultSet.getString("cfilename");

                list.add(new Category(Id, Title, Description, cFileName));
            }

        }catch (Exception e){

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

        return list;
    }

    public boolean savePost(Post postData){

        try{

            conn = ConnectionFactory.getConn();
            String query = "insert into post(pTitle, pContent, pCode, pPic, pDateTime, cId, UserId) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, postData.getpTitle());
            preparedStatement.setString(2, postData.getpContent());
            preparedStatement.setString(3, postData.getpCode());
            preparedStatement.setString(4, postData.getpPic());
            preparedStatement.setString(5, postData.getpDateTime());
            preparedStatement.setInt(6, postData.getcId());
            preparedStatement.setInt(7, postData.getUserId());

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

    public List<Post> getAllPost(){
        List<Post> list = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConn();

            String query = "select* from post ORDER BY pId DESC";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

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

    public List<Post> getPostByCategoryId(int categoryId){
        List<Post> list = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConn();

            String query = "select* from post where cId = ? ORDER BY pId DESC";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, categoryId);

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

    public Post getPostById(int id){
        Post post = null;

        try{
            conn = ConnectionFactory.getConn();

            String query = "select* from post where pId = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int postId = resultSet.getInt("pId");
                String postTitle = resultSet.getString("pTitle");
                String postContent = resultSet.getString("pContent");
                String postCode = resultSet.getString("pCode");
                String postPic = resultSet.getString("pPic");
                String postDateTime = resultSet.getString("pDateTime");
                int postCategoryId = resultSet.getInt("cId");
                int postUserId = resultSet.getInt("userId");

                post = new Post(postId, postTitle, postContent, postCode, postPic, postDateTime, postCategoryId, postUserId);
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

        return post;
    }

    public User getUserName(int userId){
        User user = null;

        try{
            conn = ConnectionFactory.getConn();

            String query = "select* from user where UserId = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String userName = resultSet.getString("UserName");
                String userEmail = resultSet.getString("Email");
                String picture = resultSet.getString("Profile");

                user = new User(userId, userName, userEmail, picture);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) {
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return user;
    }

    public int getTotalPostCount() {
        int count = 0;
        try{
            conn = conn = ConnectionFactory.getConn();

            String query = "SELECT COUNT(*) FROM post";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }

        return count;
    }

    public int getPostCount(int cId) {
        int count = 0;
        try{
            conn = conn = ConnectionFactory.getConn();

            String query = "SELECT COUNT(*) FROM post WHERE cId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }

        return count;
    }

    public int getTotalPostCount(int userId) {
        int count = 0;
        try{
            conn = conn = ConnectionFactory.getConn();

            String query = "SELECT COUNT(*) FROM post WHERE userId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }

        return count;
    }

    public int getPostCount(int cId, int userId) {
        int count = 0;
        try{
            conn = conn = ConnectionFactory.getConn();

            String query = "SELECT COUNT(*) FROM post WHERE cId = ? AND userId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cId);
            preparedStatement.setInt(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }

        return count;
    }

    public boolean deletePost(int postId){
        boolean result = false;
        try{
            conn = ConnectionFactory.getConn();

            String query = "DELETE FROM post WHERE pId = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, postId);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected > 0){
                result = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

        return result;
    }
}
