package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Video implements Comparable {
  private String titre, realisateur;
  private int annee;
  
  public Video(){
    this("Inconnu", "Inconnu", 0);
  }
    public Video (String[] infos) {
      this(infos[0], infos[1], Integer.parseInt(infos[3]));
    }
    public Video (String titre, String realisateur){
      this(titre, realisateur, (new GregorianCalendar()).get(Calendar.YEAR));
    }
  public Video (String titre, String realisateur, String annee){
    this(titre, realisateur, Integer.parseInt(annee));
  }
  public Video (String titre, String realisateur, int annee){
    if (titre == null || realisateur == null)
      throw new NullPointerException();
    this.titre = titre;
    this.realisateur = realisateur;
    this.annee = annee;
  }
  
  public String obtenirTitre(){
    return this.titre;
  }
  public String obtenirRealisateur(){
    return this.realisateur;
  }
  public int obtenirAnnee(){
    return this.annee;
  }

  public void fournirTitre(String titre){
    this.titre = titre;
  }
  public void fournirRealisateur(String realisateur){
    this.realisateur = realisateur;
  }
  public void fournirAnnee(int annee){
    this.annee = annee;
  }
  
  public int hashCode(){
    return annee * titre.hashCode() + realisateur.hashCode();
  }
  
  public boolean equals(Object o){
    if(!(o instanceof Video))
      return false;
    Video v = (Video)o;
    if(this.hashCode() == v.hashCode())
      return true;
    return false;
  }
  
  public int compareTo(Object o){
    if(!(o instanceof Video))
      throw new ClassCastException();
    Video v = (Video)o;
    int comparaison;
    if((comparaison = titre.compareTo(v.obtenirTitre())) != 0)
      return  comparaison;
    else if((comparaison = realisateur.compareTo(v.obtenirRealisateur())) != 0)
      return comparaison;
    else
      return (new Integer(annee)).compareTo(new Integer(v.obtenirAnnee()));
  }
  
  public String toString(){
    StringBuffer res = new StringBuffer("[");
    res.append(titre);
    res.append(", ");
    res.append(realisateur);
    res.append(", ");
    res.append(annee);
    return res.append("]").toString();
  }
}