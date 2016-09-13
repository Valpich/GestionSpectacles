package javaee.spectacle.webservices;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javaee.spectacle.beans.Reservation;
import javaee.spectacle.beans.Spectacle;


@WebService(name = "WSGestionSpectacles", targetNamespace = "http://webservices.spectacle.javaee/")
public interface WSGestionSpectacles {

  @WebResult(name = "return")
  boolean annulerSpectacle(@WebParam(name = "arg0") int codeReservation);

  @WebResult(name = "return")
  ArrayList<Spectacle> trouverSpectacle(@WebParam(name = "arg0") Spectacle spectacle);

  @WebResult(name = "return")
  int reserverSpectacle(@WebParam(name = "arg0") Reservation reservation);

  @WebResult(name = "return")
  boolean payerSpectacle(@WebParam(name = "arg0") int idReservation);

  @WebResult(name = "return")
  ArrayList<Reservation> recupererReservation(@WebParam(name = "arg0") int idClient);

}
