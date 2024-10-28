package com.happy.servlets;

import com.happy.dao.PostDao;
import com.happy.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        int postId = Integer.parseInt(req.getParameter("postId"));

        PostDao postDao = new PostDao();
        boolean result = postDao.deletePost(postId);
        System.out.println(result);

        if(result){
            String msg = "Post deleted successfully";
            session.setAttribute("msg", msg);
            resp.sendRedirect("userprofile1.jsp?userId="+userId);
        }else {
            String msg = "Post not deleted";
            session.setAttribute("msg", msg);
            resp.sendRedirect("userprofile1.jsp?userId="+userId);
        }
    }
}
