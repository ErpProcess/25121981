package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.util.Comparator;

public class CompRealisateur implements Comparator {
  public int compare(Object o1, Object o2){
    if(!(o1 instanceof Video))
      throw new ClassCastException();
    return (((Video)o1).obtenirRealisateur()).compareTo(
                                        ((Video)o2).obtenirRealisateur());
  }
}
