package com.happy.validation;

import com.happy.entity.User;

public class UserValidation {

    private static String result;

    public String userData(User user) {

       String username = user.getUsername();
       String email = user.getEmail();
       String password = user.getPassword();

       if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
           result = "Username, Email or Password is empty.";
       } else if(username.length() < 2 || username.length() > 50) {
           result = "Username length must be between 2 and 50 characters.";
       } else if (password.length() < 8 || password.length() > 16) {
           result = "Password length must be between 8 and 16 characters.";
       } else if (email.length() < 12 || email.length() > 65){
            result = "Email length must be between 12 and 50 characters.";
       } else if (!email.endsWith(".com")) {
           result = "Email must begin with a .com";
       }else {
           result = "Valid";
       }

        return result;
    }

    public String userLoginData(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            result = "Email or Password is empty.";
        }else if (password.length() < 8 || password.length() > 16) {
            result = "Wrong password";
        } else if (email.length() < 12 || email.length() > 65){
            result = "Wrong email";
        } else if (!email.endsWith(".com")) {
            result = "Wrong email";
        }else {
            result = "Valid";
        }

        return result;
    }
}
