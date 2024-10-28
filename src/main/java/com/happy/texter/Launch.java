package com.happy.texter;

import com.happy.dao.ConnectionFactory;

import java.sql.Connection;


public class Launch {
    public static void main(String[] args) {
        String email = "jayantsagar@gmail";

        if(!email.endsWith(".com")){
            System.out.println("Done");
        }else{
            System.out.println("Nothing");
        }
    }
}
