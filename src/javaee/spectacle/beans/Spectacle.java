package javaee.spectacle.beans;

public class Spectacle {
  private Integer id;
  private String type;
  private String titre;
  private String ville;
  private Long date;
  private Integer prix;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitre() {
    return this.titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public String getVille() {
    return this.ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public Long getDate() {
    return this.date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public Integer getPrix() {
    return this.prix;
  }

  public void setPrix(Integer prix) {
    this.prix = prix;
  }


  public Spectacle() {

  }

  public Spectacle(Integer id, String type, String titre, String ville, Long date, Integer prix) {
    this.id = id;
    this.type = type;
    this.titre = titre;
    this.ville = ville;
    this.date = date;
    this.prix = prix;
  }

  /**
   * Méthode qui comparer si les champs non nuls d'un spectacle avec un autre passé en paramétre
   * sont égaux Si oui, la méthode retourne vrai Sinon elle retourne faux
   */
  public boolean partialEquals(Spectacle spectacle) {
    if (spectacle.getType() != null && this.getType() != null) {
      if (!spectacle.getType().equals(this.getType())) {
        return false;
      }
    }
    if (spectacle.getVille() != null && this.getVille() != null) {
      if (!spectacle.getVille().equalsIgnoreCase(this.getVille())) {
        return false;
      }
    }
    if (spectacle.getDate() != null && this.getDate() != null) {
      if (!spectacle.getDate().equals(this.getDate())) {
        return false;
      }
    }
    if (spectacle.getPrix() != null && this.getPrix() != null) {
      if (!spectacle.getPrix().equals(this.getPrix())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return new String("Spectacle Id: " + this.id + " Type: " + this.type + " Titre: " + this.titre
        + " Ville: " + this.ville + " Date: " + this.date + " Prix: " + this.prix);
  }

}
