package com.nfit.yaoliusan.myblog.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    public static Connection getConnection() throws Exception {
        String user = "sa";
        String password = "sa";
        String dataBase = "xxx";
        String host = "localhost:1434";
        String url = "jdbc:sqlserver://" + host + ";databaseName=" + dataBase + ";user=" + user + ";password=" + password;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }
}