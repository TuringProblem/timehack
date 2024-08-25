package data.buffer;

import data.DatabaseConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Buffer {

  public static void executeSQL(String sql) {
    Connection con = DatabaseConnection.connect();

    if (con != null) {
      try (Statement stmnt = con.createStatement()){
        stmnt.execute(sql);
        System.out.println(("SQL statement executed successfully.\n"));
      } catch (SQLException e) {
        System.out.println("Failed to establish a connection to the Database\nTry again: sorry :/\n");
      }
    } else {
      System.out.println("Failed to establish a database connection\n");
    }
  }
}
