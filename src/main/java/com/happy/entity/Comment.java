package com.happy.entity;

public class Comment {
    private int commentId;
    private int postId;
    private String username;
    private String userPic;
    private String comment;
    private String dateTime;
    private int userId;

    public Comment(int commentId, int postId, String username, String userPic, String comment, String dateTime, int userId) {
        this.commentId = commentId;
        this.postId = postId;
        this.username = username;
        this.userPic = userPic;
        this.comment = comment;
        this.dateTime = dateTime;
        this.userId = userId;
    }

    public Comment(int postId, String username, String userPic, String comment, String dateTime, int userId) {
        this.postId = postId;
        this.username = username;
        this.userPic = userPic;
        this.comment = comment;
        this.dateTime = dateTime;
        this.userId = userId;
    }



    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
