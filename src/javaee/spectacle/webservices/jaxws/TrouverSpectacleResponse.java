
package javaee.spectacle.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.18 Wed Mar 16 20:44:57 CET 2016 Generated source
 * version: 2.7.18
 */

@XmlRootElement(name = "trouverSpectacleResponse",
    namespace = "http://webservices.spectacle.javaee/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trouverSpectacleResponse", namespace = "http://webservices.spectacle.javaee/")

public class TrouverSpectacleResponse {

  @XmlElement(name = "return")
  private java.util.ArrayList<javaee.spectacle.beans.Spectacle> _return;

  public java.util.ArrayList<javaee.spectacle.beans.Spectacle> getReturn() {
    return this._return;
  }

  public void setReturn(java.util.ArrayList<javaee.spectacle.beans.Spectacle> new_return) {
    this._return = new_return;
  }

}

