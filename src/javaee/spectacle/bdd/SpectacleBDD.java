package javaee.spectacle.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaee.spectacle.beans.Spectacle;

public class SpectacleBDD {

  private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/bdd_spectacles";
  private static final String DEFAULT_USER = "119920_eseo";
  private static final String DEFAULT_PASSWORD = "eseo";

  private Connection connection;

  public SpectacleBDD() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // Renvoie tous les spectacles de la base de données
  public ArrayList<Spectacle> requestSpectacle() {
    final String sql_query = "select * from spectacles";
    final ArrayList<Spectacle> spectaclesArray = new ArrayList<Spectacle>();
    try {
      this.connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
      final Statement stmt = this.connection.createStatement();
      if (stmt.execute(sql_query)) {
        final ResultSet rset = stmt.getResultSet();
        while (rset.next()) {
          spectaclesArray.add(new Spectacle(rset.getInt(1), rset.getString(2), rset.getString(3),
              rset.getString(4), rset.getTimestamp(5).getTime(), rset.getInt(6)));
        }
        rset.close();
      }
      stmt.close();
      this.connection.close();
    } catch (final SQLException e1) {
      e1.printStackTrace();
    }
    return spectaclesArray;
  }

}
