package com.happy.servlets;

import com.happy.dao.LikeDao;
import com.happy.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int postId = Integer.parseInt(req.getParameter("postId"));
        int userId = user.getUserId();

        LikeDao likeDao = new LikeDao();
        boolean result = likeDao.deleteLike(postId, userId);

        if(result){
            String msg = "Post unliked successfully";
            session.setAttribute("msg", msg);
            resp.sendRedirect("showblogpost.jsp?postId="+postId);
        }else{
            String msg = "Post not unliked";
            session.setAttribute("msg", msg);
            resp.sendRedirect("showblogpost.jsp?postId="+postId);
        }
    }
}
