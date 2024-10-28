package com.happy.servlets;

import com.happy.dao.CommentDao;
import com.happy.entity.Comment;
import com.happy.entity.User;
import com.happy.helper.DateTime;
import com.happy.validation.CommentValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int postId = Integer.parseInt(req.getParameter("postId"));
        String username = user.getUsername();
        String userPic = user.getProfile();
        String comment = req.getParameter("commentArea");
        String dateTime = DateTime.getCurrentDateTime();
        int userId = user.getUserId();

        Comment commentData = new Comment(postId, username, userPic, comment, dateTime, userId);

        CommentValidation commentValidation = new CommentValidation();
        boolean validate = commentValidation.commentValid(commentData);

        if(!validate){
            session.setAttribute("msg", "Comment must be at least 5 characters long");
            resp.sendRedirect("showblogpost.jsp?postId="+postId);
        }else{
            CommentDao commentDao = new CommentDao();
            boolean result = commentDao.insertComment(commentData);
            if(result){
                session.setAttribute("msg","Comment successfully send!");
                resp.sendRedirect("showblogpost.jsp?postId="+postId);
            }else{
                session.setAttribute("msg","Comment not send!");
                resp.sendRedirect("showblogpost.jsp?postId="+postId);
            }

        }


    }
}
