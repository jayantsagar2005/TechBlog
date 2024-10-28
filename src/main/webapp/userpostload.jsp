<%@page import="com.happy.dao.LikeDao"%>
<%@page import="com.happy.dao.CommentDao"%>
<%@page import="com.happy.dao.PostDao"%>
<%@page import="com.happy.dao.UserProfileDao"%>
<%@page import="java.util.List"%>
<%@page import="com.happy.entity.Post"%>
<%@page import="com.happy.entity.User"%>

<div class="row">
<%
    User user = (User) session.getAttribute("user");

    UserProfileDao userProfileDao = new UserProfileDao();

    int cId = Integer.parseInt(request.getParameter("cid"));
    int userId = Integer.parseInt(request.getParameter("userId"));

    List<Post> posts = null;
    if(cId == 0){
        posts = userProfileDao.getUserAllPost(userId);
    } else{
        posts = userProfileDao.getUserPostByCategoryId(cId, userId);
    }
    if(posts.size() == 0){
        out.println("<h1 class='ml-5'>No posts found.</h1>");
        return;
    }
    for(Post post : posts) {
        String str = "";
        String s = post.getpContent();
        for(int i=0; i<100; i++){
            char ch = s.charAt(i);
            str = str + ch;
        }
        str = str + "...";
%>
    <div class="col-6 mt-2">
        <div class="card" style="height: 30rem;">
            <%
                if(!post.getpPic().isEmpty()) {
            %>
                    <img class="card-img-top" src="PostPic/<%=post.getpPic()%>" alt="image">
            <%
                } else {
            %>
                    <img class="card-img-top" src="PostPic/default.jpg" alt="image">
            <%
                 }
            %>
            <div class="card-body">
                <h5 class="card-title"><%= post.getpTitle() %></h5>
                <p class="card-text"><%= str %></p>
                <small class="text-muted">Posted was <%= post.getpDateTime() %></small><br>
            </div>
            <div class="card-footer text-center">
                <%
                    LikeDao likeDao = new LikeDao();
                    CommentDao commentDao = new CommentDao();
                    int commentCount = commentDao.countCommentOnPost(post.getpId());
                %>
                <a href="showblogpost1.jsp?postId=<%=post.getpId()%>" class="btn primary-background text-white btn-sm ml-2">Read More..</a>
            </div>
        </div>
    </div>
<%
    }
%>

</div>