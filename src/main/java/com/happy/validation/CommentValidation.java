package com.happy.validation;

import com.happy.entity.Comment;

public class CommentValidation {

    public boolean commentValid(Comment comment){
        boolean result = true;

        String com = comment.getComment();

        int comLength = com.length();

        if(comLength > 1000 || comLength == 0){
            result = false;
        }

        return result;
    }
}
