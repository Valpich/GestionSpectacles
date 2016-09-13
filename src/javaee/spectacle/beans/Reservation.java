package javaee.spectacle.beans;

/**
 * Classe représantant une réservation
 **/
public class Reservation {

  private int idReservation;
  private int idClient;
  private Integer nombrePlaces;
  private Integer idSpectacle;
  private Boolean paiement;

  public Integer getNombrePlaces() {
    return this.nombrePlaces;
  }

  public void setNombrePlaces(Integer nombrePlaces) {
    this.nombrePlaces = nombrePlaces;
  }

  public Integer getIdSpectacle() {
    return this.idSpectacle;
  }

  public void setIdSpectacle(Integer idSpectacle) {
    this.idSpectacle = idSpectacle;
  }

  public Boolean getPaiement() {
    return this.paiement;
  }

  public void setPaiement(Boolean paiement) {
    this.paiement = paiement;
  }

  public Integer getIdReservation() {
    return this.idReservation;
  }

  public void setIdReservation(Integer idReservation) {
    this.idReservation = idReservation;
  }

  public int getIdClient() {
    return this.idClient;
  }

  public void setIdClient(int idClient) {
    this.idClient = idClient;
  }

  public void setIdReservation(int idReservation) {
    this.idReservation = idReservation;
  }

  public Reservation() {

  }

  /**
   * Constructeur permettant de décrire une réservation à l'aide du nombre de places réservés, du
   * spectacle reservé, et du numero de réservation
   */
  public Reservation(Integer nombrePlaces, Integer idSpectacle, Integer idReservation,
      Boolean paiementEffectue) {
    super();
    this.nombrePlaces = nombrePlaces;
    this.idSpectacle = idSpectacle;
    this.paiement = paiementEffectue;
    this.idReservation = idReservation;
  }

  @Override
  public String toString() {
    return new String("Nombre de places : " + this.nombrePlaces + ", id spectacle : "
        + this.idSpectacle + ", id client : " + this.idClient + ", paiement : " + this.paiement
        + ", id reservation :" + this.idReservation);
  }

}
