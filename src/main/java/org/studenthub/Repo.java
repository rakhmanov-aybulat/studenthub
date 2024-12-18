package org.studenthub;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Repo {
    public static Connection conn;
    Repo() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite::memory:");
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet executePreparedQuery(String query, Object... params) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int executePreparedUpdate(String query, Object... params) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeSqlFile(String resourcePath) {
        try (InputStream inputStream =
                     getClass().getResourceAsStream(resourcePath);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(inputStream))) {

            conn.setAutoCommit(false); // Start transaction
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
            String[] statements = sql.toString().split(";");
            try {
                for (String statement : statements) {
                    if (!statement.trim().isEmpty()) {
                        executeUpdate(statement);
                    }
                }
                conn.commit(); // Commit transaction
            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction on failure
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true); // Reset to auto-commit mode
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    
}
