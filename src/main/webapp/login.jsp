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
                clip-path: polygon(50% 0%, 79% 0, 100% 0, 100% 99%, 62% 92%, 51% 100%, 15% 93%, 0 100%, 0 0, 21% 0);
            }
            .temp-background{
                background: #81d4fa;
            }
            .button{
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <main class="d-flex align-items-center temp-background mt-1 banner-background" style="height: 80vh;">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 offset-md-4">
                        <div class="card">
                            <div class="card-header primary-background text-dark text-center">
                                <span class="fa fa-user-plus fa-3x"></span>
                                <br>
                                <h3>LOGIN</h3>
                            </div>
                            <div class="card-body">
                                <form action="LoginServlet" method="post">
                                  <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input type="email" required class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" placeholder="Enter email">
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                  </div>
                                  <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" required class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
                                    <a href="forgotpassword.jsp"><small class="ml-2">Forgot your password</small></a>
                                  </div>
                                  <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="check">
                                    <label class="form-check-label" for="exampleCheck1">You are human</label>
                                  </div>

                                   <%
                                        String msg  = (String) session.getAttribute("msg");
                                        if(msg != null) {
                                   %>
                                           <p style="text-align:center;"><%@include file="message.jsp" %></p>
                                   <%
                                        }
                                   %>


                                  <div class="button">
                                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                                  </div>
                                </form>
                            </div>
                            <div class="footer">
                                <p class="text-center">Already not have an account? <a href="register.jsp">Register</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>