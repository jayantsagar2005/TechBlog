package com.happy.servlets;

import com.happy.dao.PostDao;
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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String check = req.getParameter("check");

        if(check == null) {
            String msg = "Tick check me out box";
            session.setAttribute("msg", msg);
            resp.sendRedirect("login.jsp");
        }else{
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String dateTime = DateTime.getCurrentDateTime(); // date time format

            UserValidation validation = new UserValidation();
            String valid = validation.userLoginData(email, password);

            if(valid.equals("Valid")){

                UserDao userDao = new UserDao();
                User user = userDao.getUser(email, password);

                if(user == null){
                    String msg = "Email or password incorrect";
                    session.setAttribute("msg", msg);
                    resp.sendRedirect("login.jsp");
                }else{
                    session.setAttribute("user", user);
                    resp.sendRedirect("profile.jsp");
                }

            }else{
                session.setAttribute("msg", valid);
                resp.sendRedirect("login.jsp");
            }// user data validation
        }



    }
}
