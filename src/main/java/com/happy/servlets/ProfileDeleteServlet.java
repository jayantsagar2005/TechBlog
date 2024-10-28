package com.happy.servlets;

import com.happy.dao.ProfileDeleteDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ProfileDeleteServlet")
public class ProfileDeleteServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int userId = Integer.parseInt(req.getParameter("userId"));

       // Delete user from database

        ProfileDeleteDao dao = new ProfileDeleteDao();

        boolean result = dao.deleteProfile(userId);

        if(result){
            session.removeAttribute("user");
            String msg = "Your profile has been deleted successfully.";
            session.setAttribute("msg", msg);
            resp.sendRedirect("index.jsp");
        }else {
            String msg = "Your profile has not been deleted.";
            session.setAttribute("msg", msg);
            resp.sendRedirect("userprofile1.jsp?userId=<%=userId%>");
        }

    }
}
