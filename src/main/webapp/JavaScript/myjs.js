function doLike(postId, userId){
    const d = {
        postId: postId,
        userId: userId,
        operation: 'like'
    }
    $(".btn").removeClass('btn-outline-primary');
    $.ajax({
        url: "LikeServlet",
        data: d,
        success: function(data, textStatus, jqXHR){
            if(data.trim() == 'true'){
                let c = $(".like-counter").html();
                c++;
                $('.like-counter').html(c);
                $(".btn").addClass('btn-primary');
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(data);
        }
    })
}

onclick="doLike(<%= post.getpId() %>,<%= user1.getUserId() %>)"
