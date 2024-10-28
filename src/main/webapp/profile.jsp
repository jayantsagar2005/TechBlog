<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.happy.entity.User"%>
<%@page import="com.happy.entity.Category"%>
<%@page import="com.happy.dao.PostDao"%>
<%@page import="java.util.ArrayList"%>


<%
    User user = (User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="CSS/style.css">
        <script src="JavaScript/myjs.js" defer></script>
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
            .card-img-top{
                margin-left:5px;
                margin-top:5px;
                height: 180px;
                width: 370px;
                border: 1px solid #ddd;
                border-radius: 8px;
            }
            .profile-img{
                height: 120px;
                width: 120px;
            }
            .user-sm-img{
                height: 30px;
                width: 30px;
                border-radius: 50%;
            }

        </style>
    </head>
    <body>

    <!-- Navigation Bar Start -->

        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
          <a class="navbar-brand" href="index1.jsp"> <span class="fa fa-android fa-2x mr-2 fa-spin"></span> HAPPY' BLOG</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="index1.jsp">Home <span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categories</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="#">Programming Language</a>
                  <a class="dropdown-item" href="#">Project Implementation</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#">Data Structure</a>
                </div>
              </li>
              <li class="nav-item">
                 <a class="nav-link" href="#">Contact</a>
              </li>
               <li class="nav-item">
                 <a class="nav-link" href="userprofile1.jsp?userId=<%= user.getUserId() %>"><span></span>My Post</a>
               </li>
              <li class="nav-item">
                 <a class="nav-link" href="#" data-toggle="modal" data-target="#addPost"><span class="fa fa-edit"></span>Do Post</a>
               </li>
            </ul>
            <ul class="navbar-nav mr-right">
                <li class="nav-item">
                   <a class="nav-link" href="" data-toggle="modal" data-target="#profile-modal"><img src="ProfilePic/<%= user.getProfile() %>" alt="profile pic" class="user-sm-img mr-2"><%= user.getUsername() %></a>
                </li>
                <li class="nav-item">
                   <a class="nav-link" style="color:red;" href="LogOutServlet"><span class="fa fa-sign-in mr-1"></span>LogOut</a>
                </li>
            </ul>
          </div>
        </nav>

    <!-- Navigation Bar End -->

    <%
         String msg = (String) session.getAttribute("msg");
         if(msg != null) {
    %>
             <h3 class="mb-5" style="text-align:center;"><%@include file="message.jsp" %></h3>
    <%
         }
    %>

    <!-- main body start -->

        <main class="">
            <div class="container-fluid">
                <div class="row mt-4">
                    <div class="col-md-2">
                        <div class="list-group">
                            <%
                                PostDao postDao = new PostDao();
                                int totalPosts = postDao.getTotalPostCount();
                            %>
                                <a href="profile.jsp" onclick="getAllPosts(0, this)" class="c-link list-group-item list-group-item-action active"> All Posts (<%= totalPosts %>) </a>
                            <%

                               ArrayList<Category> list = postDao.getAllCategory();
                               for (Category category : list) {
                                    int totalPosts1 = postDao.getPostCount(category.getcId());
                            %>
                                        <a href="#" onclick="getAllPosts(<%= category.getcId() %>, this)" class="c-link list-group-item list-group-item-action"> <%= category.getcName()+"("+totalPosts1+")" %> </a>
                            <%
                                }
                            %>

                        </div>
                    </div>

                    <!-- column all post content -->

                    <div class="col-md-10">
                        <div class="container text-center" id="loader">
                            <i class="fa fa-refresh fa-spin fa-4x"></i>
                            <h3 class="mt-3">Loading...</h3>
                        </div>
                        <div class="container-fluid" id="postcontainer">

                        </div>
                    </div>
                </div>
            </div>
        </main>

    <!-- main body end -->

    <!-- Profile Modal Start -->

        <div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
              <div class="modal-header primary-background">
                <h4 class="modal-title" id="exampleModalLongTitle">YOUR ACCOUNT DETAILS</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                    <div class="container text-center">
                        <img src="ProfilePic/<%= user.getProfile() %>" alt="profile pic" class="img-fluid profile-img" style="border-radius:50%; max-width:120px;" >
                        <br>
                        <h5 class="modal-title mt-4" id="exampleModalLongTitle"><%= user.getUsername() %></h5>
                        <div id="profile-details">
                            <table class="table mt-3">
                              <tbody>
                                <tr>
                                  <th scope="row">User ID : </th>
                                  <td><%= user.getUserId() %></td>
                                </tr>
                                <tr>
                                  <th scope="row">Email : </th>
                                  <td><%= user.getEmail() %></td>
                                </tr>
                                <tr>
                                  <th scope="row">Register Date : </th>
                                  <td><%= user.getDate() %></td>
                                </tr>
                              </tbody>
                            </table>
                        </div>
                        <div id="profile-edit" style="display:none">
                            <h3 class="mt-3">Please Edit Carefully</h3>
                            <form action="EditProfileServlet" method="post" enctype="multipart/form-data">
                                <table class="table">
                                    <tr>
                                        <th scope="row">User ID : </th>
                                        <td><%= user.getUserId() %></td>
                                     </tr>
                                     <tr>
                                        <th scope="row">Username </th>
                                        <td> <input type="text" class="form-control" name="name" value="<%= user.getUsername() %>"></td>
                                     </tr>
                                     <tr>
                                        <th scope="row">Email </th>
                                        <td> <input type="email" class="form-control" name="email" value="<%= user.getEmail() %>"></td>
                                     </tr>
                                     <tr>
                                        <th scope="row">Password </th>
                                        <td> <input type="password" class="form-control" name="password" value="<%= user.getPassword() %>"></td>
                                     </tr>
                                     <tr>
                                        <th scope="row">Profile </th>
                                        <td> <input type="file" class="form-control" name="image"></td>
                                     </tr>
                                </table>
                                <div class="container text-center">
                                    <button type="submit" class="btn btn-outline-primary">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="edit-profile">Edit</button>
              </div>
            </div>
          </div>
        </div>

    <!-- Profile Modal End -->

    <!-- Post Modal Start -->

        <!-- Modal -->
        <div class="modal fade" id="addPost" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Provide the blog details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form action="AddPostServlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="category">Category*</label>
                        <select class="form-control" name="category">
                            <option value="0" selected >--Select Category--</option>
                            <%
                               for (Category category : list) {
                            %>
                                    <option value="<%= category.getcId() %>"> <%= category.getcName() %> </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Title">Title*</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Enter post title">
                    </div>
                    <div class="form-group">
                        <label for="Content">Content*</label>
                        <textarea class="form-control" id="content" name="content" rows="4" placeholder="Enter post content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="Code">Code</label>
                        <textarea class="form-control" id="code" name="code" rows="3" placeholder="Enter post code (Optional)"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="image">Image</label>
                        <input type="file" class="form-control-file" id="image" name="image">
                    </div>
                    <div class="container text-center">
                        <button type="submit" class="btn btn-primary">Share Your Post</button>
                    </div>
                </form>
              </div>
            </div>
          </div>
        </div>

    <!-- Post Modal End -->

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <script>
            $(document).ready(function(){
                let editStatus = false;
                $('#edit-profile').click(function(){
                    if(editStatus == false){
                        $("#profile-details").hide();
                        $("#profile-edit").show();
                        editStatus = true;
                        $(this).text("Back");
                    }else{
                        $("#profile-details").show();
                        $("#profile-edit").hide();
                        editStatus = false;
                        $(this).text("Edit");
                    }
                })
            });
        </script>

        <script>
            function getAllPosts(categoryId, temp){
                $("#loader").show();
                $("#postcontainer").hide();
                $(".c-link").removeClass('active');
                $.ajax({
                    url: "loadpost.jsp",
                    data: {cid: categoryId},
                    success: function (data, textStatus, jqXHR){
                        $("#loader").hide();
                        $("#postcontainer").show();
                        $('#postcontainer').html(data);
                        $(temp).addClass('active');
                    }
                })
            }

            $(document).ready(function(e){
                let allPostRef = $('.c-link')[0]
                getAllPosts(0, allPostRef)
            })
        </script>

    </body>
</html>