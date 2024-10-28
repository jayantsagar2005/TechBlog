package com.happy.servlets;

import com.happy.dao.UserDao;
import com.happy.entity.User;
import com.happy.helper.DateTime;
import com.happy.validation.UserValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String check = req.getParameter("check");

        if(check == null){
            String msg = "Please Agree with Terms and Conditions";
            session.setAttribute("msg", msg);
            resp.sendRedirect("register.jsp");

        }else{
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            String dateTime = DateTime.getCurrentDateTime(); // date time format

            User user = new User(username, email, password, dateTime);

            UserValidation validation = new UserValidation();
            String valid = validation.userData(user);  // user data validation

            if(valid.equals("Valid")){

                UserDao userDao = new UserDao();
                boolean result = userDao.saveUser(user); // user data  save database

                if(result){
                    String msg = "User registered successfully";
                    session.setAttribute("msg", msg);
                    resp.sendRedirect("login.jsp");
                }else{
                    String msg = "Failed to register user";
                    session.setAttribute("msg", msg);
                    resp.sendRedirect("register.jsp");
                }

            }else{
                session.setAttribute("msg", valid);
                resp.sendRedirect("register.jsp");
            }


        }
    }
}
