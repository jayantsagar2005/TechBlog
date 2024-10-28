<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.happy.entity.User"%>
<%@page import="com.happy.dao.UserProfileDao"%>
<%@page import="com.happy.dao.PostDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.happy.entity.Category"%>


<%
    User user = (User) session.getAttribute("user");

    int userId = Integer.parseInt(request.getParameter("userId"));
    UserProfileDao userProfileDao = new UserProfileDao();
    User user1 = userProfileDao.getUser(userId);

%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="CSS/style.css">
        <style>
            .banner-background{
                clip-path: polygon(50% 0%, 79% 0, 100% 0, 100% 99%, 69% 93%, 51% 100%, 15% 93%, 0 100%, 0 0, 21% 0);
            }
            .temp-background{
                background: #81d4fa;
            }
            .button{
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .profile-img{
                height: 120px;
                width: 120px;
            }
            .card-img-top{
                margin-left:5px;
                margin-top:5px;
                height: 200px;
                width: 380px;
                border: 1px solid #ddd;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>

        <div class="container-fluid mt-1">
            <div class="rows">
                <div class="col-12">
                    <div class="jumbotron banner-background text-white temp-background">
                        <div class="container">
                            <h1 class="display-4">Welcome to My Blogs</h1>
                            <p class="lead">Discover the latest technical news, articles, and updates from the tech world.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                    <div class="list-group">
                        <%
                            PostDao postDao = new PostDao();
                            int totalPosts = postDao.getTotalPostCount(user1.getUserId());
                        %>
                            <a href="userprofile.jsp?userId=<%= user1.getUserId() %>" onclick="getAllPosts(0, <%= user1.getUserId() %>, this)" class="c-link list-group-item list-group-item-action active"> All Posts (<%= totalPosts %>) </a>
                        <%
                           ArrayList<Category> list = postDao.getAllCategory();
                           for (Category category : list) {
                                int totalPosts1 = postDao.getPostCount(category.getcId(), user1.getUserId());
                        %>
                                    <a href="#" onclick="getAllPosts(<%= category.getcId() %>, <%= user1.getUserId() %>, this)" class="c-link list-group-item list-group-item-action"> <%= category.getcName()+"("+totalPosts1+")" %> </a>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-7">
                    <div class="container text-center" id="loader">
                        <i class="fa fa-refresh fa-spin fa-4x"></i>
                        <h3 class="mt-3">Loading...</h3>
                    </div>
                    <div class="container-fluid" id="postcontainer">

                    </div>
                </div>
                <div class="col-3">
                    <div class="card text-center bg-dark">
                      <div class="text-center mt-2">
                        <img src="ProfilePic/<%= user1.getProfile() %>" alt="profile pic" class="img-fluid profile-img" style="border-radius:50%;" >
                      <div>
                      <div class="card-body text-white">
                        <h4><%= user1.getUsername() %></h4>
                        <p>User Id: <%= user1.getUserId() %></p>
                        <p>Gmail: <%= user1.getEmail() %></p>
                        <p>Register Date: <%= user1.getDate() %></p>
                      </div>
                    </div>
                </div>
            </div>
            <div>
        </div>

        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <script>
            function getAllPosts(categoryId, userId, temp){
                console.log("function run")
                $("#loader").show();
                $("#postcontainer").hide();
                $(".c-link").removeClass('active');
                const da = {
                    cid: categoryId,
                    userId: userId
                }
                $.ajax({
                    url: "userpostload.jsp",
                    data: da,
                    success: function (data, textStatus, jqXHR){
                        $("#loader").hide();
                        $("#postcontainer").show();
                        $('#postcontainer').html(data);
                        $(temp).addClass('active');
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        console.log(data);
                    }
                })
            }

            $(document).ready(function(e){
                let allPostRef = $('.c-link')[0]
                getAllPosts(0, <%= userId %>, allPostRef)
            })
        </script>

    </body>
</html>