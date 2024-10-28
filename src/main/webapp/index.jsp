<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>

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
            .card-img-top{
                margin-left:5px;
                margin-top:5px;
                height: 180px;
                width: 330px;
                border: 1px solid #ddd;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>

        <div class="container-fluid p-0 m-0">
            <div class="jumbotron primary-background text-white banner-background">
                <div class="container">
                    <h1>WELCOME TO HAPPY' BLOG</h1>
                    <p>Welcome to happy' blog, world of technology</p>
                    <p>Programming is the process of designing and building executable software to accomplish a specific task or solve a problem. It involves writing code in various programming languages such as Python, Java, C++, and more, which allows machines to perform operations efficiently.</p>
                    <p>In addition to its technical aspects, programming also enhances problem-solving skills and logical thinking. Programmers must not only understand syntax and algorithms but also apply creativity and critical thinking to design efficient and scalable solutions. </p>
                    <a href="register.jsp" class="btn btn-outline-light btn-lg"><span class="fa fa-sign-in"></span> Start! Its Free</a>
                    <a href="login.jsp" class="btn btn-outline-light btn-lg"><span class="fa fa-user-plus fa-spin"></span>LogIn</a>
                 </div>
            </div>
        </div
        <br>

        <!-- main body start -->

            <main class="">
                <div class="container">
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <div class="container text-center" id="loader">
                                <i class="fa fa-refresh fa-spin fa-4x"></i>
                                <h3 class="mt-3">Loading...</h3>
                            </div>
                            <%
                                 String msg  = (String) session.getAttribute("msg");
                                 if(msg != null) {
                            %>
                                    <p style="text-align:center;"><%@include file="message.jsp" %></p>
                            <%
                                 }
                            %>
                            <div class="container-fluid" id="postcontainer">

                            </div>
                        </div>
                    </div>
                </div>
            </main>

        <!-- main body end -->

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <script>
            function getAllPosts(categoryId, temp){
                $("#loader").show();
                $("#postcontainer").hide();
                $(".c-link").removeClass('active');
                $.ajax({
                    url: "loadpost1.jsp",
                    data: {cid: categoryId},
                    success: function (data, textStatus, jqXHR){
                        console.log(data);
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