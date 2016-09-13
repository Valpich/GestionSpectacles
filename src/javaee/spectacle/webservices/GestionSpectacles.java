package javaee.spectacle.webservices;

import java.util.ArrayList;

import javax.jws.WebService;

import javaee.spectacle.bdd.ReservationBDD;
import javaee.spectacle.bdd.SpectacleBDD;
import javaee.spectacle.beans.Reservation;
import javaee.spectacle.beans.Spectacle;

@WebService(targetNamespace = "http://webservices.spectacle.javaee/",
    endpointInterface = "javaee.spectacle.webservices.WSGestionSpectacles",
    portName = "GestionSpectaclesPort", serviceName = "GestionSpectaclesService")
public class GestionSpectacles implements WSGestionSpectacles {

  @Override
  public boolean annulerSpectacle(int codeReservation) {
    final ReservationBDD reservationBDD = new ReservationBDD();
    try {
      reservationBDD.annulerReservation(codeReservation);
    } catch (final Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public ArrayList<Spectacle> trouverSpectacle(Spectacle spectacle) {
    ArrayList<Spectacle> spectaclesArray = new ArrayList<Spectacle>();
    final ArrayList<Spectacle> spectaclesArrayResult = new ArrayList<Spectacle>();

    final SpectacleBDD gestionBDD = new SpectacleBDD();
    spectaclesArray = gestionBDD.requestSpectacle();

    int i = 0;
    final int orignalSize = spectaclesArray.size();
    while (i < orignalSize) {
      if (spectacle.partialEquals(spectaclesArray.get(i)) == true) {
        spectaclesArrayResult.add(spectaclesArray.get(i));
      }
      i++;
    }
    return spectaclesArrayResult;
  }

  @Override
  public int reserverSpectacle(Reservation reservation) {
    try {
      final ReservationBDD reservationBDD = new ReservationBDD();
      final int numero = reservationBDD.reserverSpectacle(reservation);
      return numero;
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public boolean payerSpectacle(int idReservation) {
    boolean test = false;
    try {
      new ReservationBDD().payerSpectacle(idReservation);
      test = true;
    } catch (final Exception e) {
    }
    return test;
  }

  @Override
  public ArrayList<Reservation> recupererReservation(int idClient) {
    ArrayList<Reservation> reservation = new ArrayList<Reservation>();
    try {
      reservation = new ReservationBDD().recupererReservation(idClient);
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return reservation;
  }

}
