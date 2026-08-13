package com.example.barstorybackend;

import java.sql.Connection;
import java.sql.DriverManager;

public class PureDatabaseTest {
    public static void main(String[] args) {
        // 这里的密码填你 application.properties 里的真实密码 010318
        String url = "jdbc:postgresql://localhost:5432/barstory_database";
        String user = "postgres";
        String password = "010318";

        try {
            System.out.println("正在尝试连接 PostgreSQL...");
            // 尝试获取物理连接
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("太棒了！数据库连接完全正常！");
            connection.close();
        } catch (Exception e) {
            System.err.println("数据库连接失败了，快看原因：");
            e.printStackTrace();
        }
    }
}