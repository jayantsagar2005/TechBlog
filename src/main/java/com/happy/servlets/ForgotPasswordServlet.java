package com.happy.servlets;

import com.happy.dao.UserDao;
import com.happy.entity.User;
import com.happy.validation.UserValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(username, email, password);

        UserValidation validation = new UserValidation();
        String valid = validation.userData(user);

        if(valid.equals("Valid")){

            UserDao userDao = new UserDao();
            boolean result = userDao.forgotPassword(user); // user data forgot password database

            if(result){
                String msg = "Your password successfully forgot";
                session.setAttribute("msg", msg);
                resp.sendRedirect("login.jsp");
            }else{
                String msg = "Failed to forgot your password";
                session.setAttribute("msg", msg);
                resp.sendRedirect("forgotpassword.jsp");
            }

        }else{
            session.setAttribute("msg", valid);
            resp.sendRedirect("forgotpassword.jsp");
        }

    }
}
