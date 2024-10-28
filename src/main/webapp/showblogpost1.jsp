<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.happy.entity.User"%>
<%@page import="com.happy.dao.PostDao"%>
<%@page import="com.happy.dao.LikeDao"%>
<%@page import="com.happy.dao.CommentDao"%>
<%@page import="com.happy.entity.Post"%>
<%@page import="com.happy.entity.Comment"%>
<%@page import="com.happy.entity.Category"%>
<%@page import="java.util.ArrayList"%>

<%
    User user = (User) session.getAttribute("user");

    int postId = Integer.parseInt(request.getParameter("postId"));

    PostDao postDao = new PostDao();
    Post post = postDao.getPostById(postId);
    if(post == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String title = post.getpTitle();
    String content = post.getpContent();
    String code = post.getpCode();
    String pic = post.getpPic();
    String date = post.getpDateTime();
    int categoryId = post.getcId();
    int userId = post.getUserId();

%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%= title %></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/prismjs@1.29.0/themes/prism.css" rel="stylesheet" />
        <link rel="stylesheet" href="CSS/style.css">
        <script src="JavaScript/myjs.js" defer></script>
        <style>
            .banner-background{
                clip-path: polygon(50% 0%, 79% 0, 100% 0, 100% 99%, 62% 92%, 51% 100%, 15% 93%, 0 100%, 0 0, 21% 0);
            }
            .post-title{
                font-weight: 700;
                font-size: 30px;
            }
            .post-content{
                font-size: 18px;
                line-height: 1.6;
                white-space: pre-wrap;
                word-wrap: break-word;
                overflow-x: hidden;
                font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
            }
            .user-sm-img{
                height: 30px;
                width: 30px;
                border-radius: 50%;
            }
        </style>
    </head>
    <body>

    <%@include file="navbar.jsp" %>

    <!-- Main Content Body Start -->

         <div class="container">
            <div class="row my-4">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header">
                            <a href="profile.jsp" ><span class = "fa fa-arrow-circle-left fa-2x"></span></a>
                            <div class="text-center">
                                <h3 class="post-title"><%= title %></h3>
                                <p><%@include file="message.jsp"%></p>
                            </div>
                        </div>
                        <div class="card-body ml-2">
                            <%
                                if(!pic.isEmpty()) {
                            %>
                                    <img class="card-img-top" src="PostPic/<%= pic %>">
                            <%
                                }
                            %>
                            <br>
                            <br>
                            <div class="row">
                                <div class="col-md-7">
                                    <%
                                        User user1 = postDao.getUserName(userId);
                                    %>
                                    <strong>Posted by <a href="userprofile.jsp?userId=<%= userId %>"><%= user1.getUsername() %></a></strong>
                                </div>
                                <div class"col-md-5">
                                    <strong style="font-style:italic;">Posted was <%= date %></strong>
                                </div>
                            </div>
                            <br>
                            <pre class="post-content"><%= content %></pre>
                            <hr>
                            <div class="post-code">
                                <pre><code class="language-java"><%= code %></code></pre>
                            </div>
                        </div>
                        <div class="card-footer">
                            <%
                                LikeDao likeDao = new LikeDao();
                                CommentDao commentDao = new CommentDao();
                                int commentCount = commentDao.countCommentOnPost(postId);
                            %>
                            <a href="login.jsp" class="btn btn-outline-primary text-dark btn-sm"><i class="fa fa-thumbs-o-up"> <span class="like-counter"><%= likeDao.countLikeOnPost(post.getpId()) %></span></i></a>
                            <a href="login.jsp" class="btn btn-outline-primary text-dark btn-sm ml-2"><i class="fa fa-commenting-o" data-toggle="modal" data-target="#commentModal"> <span><%= commentCount %></span></i></a>
                        </div>
                    </div>
                </div>
            </div>
         </div>

    <!-- Main Content Body End -->

        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/prismjs@1.29.0/prism.js"></script>

    </body>
</html>