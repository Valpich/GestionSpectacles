package javaee.spectacle.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaee.spectacle.beans.Reservation;

public class ReservationBDD {
  private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/bdd_spectacles";
  private static final String DEFAULT_USER = "119920_eseo";
  private static final String DEFAULT_PASSWORD = "eseo";

  private Connection connection;

  /**
   * Constructeur permettant de vérifier la présence du driver JDBC
   **/
  public ReservationBDD() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Méthode permettant d'annuler la réservation d'un spectacle en base de donnée. Génére un
   * exception si le spectacle n'a pas pu être supprimé.
   **/
  public void annulerReservation(Integer codeReservation) throws Exception {
    PreparedStatement preparedStatement = null;
    try {
      this.connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    try {
      final String request = "DELETE FROM reservations WHERE idReservation=?;";
      try {
        preparedStatement =
            this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
      } catch (final SQLException e) {
        e.printStackTrace();
      }
      preparedStatement.setInt(1, codeReservation);
      preparedStatement.executeUpdate();
    } catch (final SQLException e) {
      e.printStackTrace();
      throw new Exception("Impossible de supprimer le spectacle!");
    }
    try {
      this.connection.close();
    } catch (final SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Méthode permettant d'enregistrer une réservation dans la base de donnée. Génére un exception si
   * la réservation n'a pas pu être enregistrée.
   **/
  public int reserverSpectacle(Reservation reservation) throws Exception {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    // connectection a la base de données
    try {
      this.connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    // recuperation des donnees du spectacle à enregistrer, ordre: (idReservation, idSpectacle,
    // idClient, nombreplaces)
    try {
      final String request = "INSERT INTO reservations VALUES (NULL, ?, ?, ?, ?);";
      try {
        preparedStatement =
            this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
      } catch (final SQLException e) {
        e.printStackTrace();
      }
      preparedStatement.setInt(1, reservation.getIdSpectacle());
      preparedStatement.setInt(2, reservation.getIdClient());
      preparedStatement.setInt(3, reservation.getNombrePlaces());
      reservation.setPaiement(false);
      preparedStatement.setBoolean(4, reservation.getPaiement());
      preparedStatement.executeUpdate();
      // recupération de l'idReservation générée
      resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        reservation.setIdReservation(resultSet.getInt(1));
      } else {
        throw new Exception("Impossible d'ajouter la réservation!");
      }
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    try {
      this.connection.close();
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return reservation.getIdReservation();
  }


  /**
   * Méthode récupérant la liste des réservations d'un client
   **/
  public ArrayList<Reservation> recupererReservation(int idClient) throws Exception {
    final ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    try {
      this.connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    try {
      final String request =
          "SELECT s.idSpectacle,s.typeSpectacle,s.titreSpectacle,s.villeSpectacle, s.dateSpectacle,r.nombreplaces,r.idReservation,r.paiementEffectue,r.idClient "
              + "FROM reservations r,spectacles s "
              + "WHERE r.idSpectacle=s.idSpectacle AND r.idClient=?;";
      try {
        preparedStatement = this.connection.prepareStatement(request);
      } catch (final SQLException e) {
        e.printStackTrace();
      }
      preparedStatement.setInt(1, idClient);
      rs = preparedStatement.executeQuery();
      try {
        while (rs.next()) {
          if (rs.getInt("idClient") != idClient) {
          } else {
            final Reservation reservation =
                new Reservation(new Integer(Integer.parseInt(rs.getString("nombreplaces"))),
                    rs.getInt("idSpectacle"), rs.getInt("idReservation"),
                    new Boolean(rs.getBoolean("paiementEffectue")));
            reservations.add(reservation);
          }
        }
      } catch (final SQLException e) {
        e.printStackTrace();
      }
    } catch (final SQLException e) {
      e.printStackTrace();
      throw new Exception("Impossible de trouver des spectacles !");
    }
    try {
      this.connection.close();
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return reservations;
  }

  public void payerSpectacle(int idReservation) throws Exception {
    PreparedStatement preparedStatement = null;
    try {
      try {
        this.connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
      } catch (final SQLException e1) {
        e1.printStackTrace();
      }
      final String requeteAjout =
          "UPDATE reservations SET paiementEffectue = 1 WHERE reservations.idReservation = ?;";
      try {
        preparedStatement = this.connection.prepareStatement(requeteAjout);
      } catch (final SQLException e) {
        e.printStackTrace();
      }
      preparedStatement.setInt(1, idReservation);
      preparedStatement.executeUpdate();
    } catch (final Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (this.connection != null) {
          this.connection.close();
        }
      } catch (final SQLException e) {
      }
    }
  }

}
