package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;

public class CollectionTreeSet {
  static final Comparator ORDRE_REALISATEUR = new CompRealisateur();
  static final Comparator ORDRE_ANNEE = new CompAnnee();
  public static void main(String[] args) {
    TreeSet ensemble = new TreeSet();
    ensemble.add(new Video("Le jour le plus long", "Ken Annakin", 1962));
    ensemble.add(new Video("Un pont trop loin", "Richard Attenborough", 1977));
    ensemble.add(new Video("Platoon", "Oliver Stone", 1986));
    ensemble.add(new Video("Full metal jacket", "Stanley Kubrik", 1987));
    ensemble.add(new Video("La ligne rouge", "Terrence Malick", 1998));
    ensemble.add(new Video("The patriot", "Roland Emmerich", 2000));
    System.out.println("Tri par titre :");
    afficherElements(ensemble); 
    TreeSet ensembleR = new TreeSet(ORDRE_REALISATEUR);
    ensembleR.addAll(ensemble);
    System.out.println("\nTri par réalisateur :");
    afficherElements(ensembleR);
    TreeSet ensembleA = new TreeSet(ORDRE_ANNEE);
    ensembleA.addAll(ensemble);
    System.out.println("\nTri par année :");
    afficherElements(ensembleA);
    SortedSet selection = 
                  ensemble.headSet(new Video("Platoon", "Oliver Stone", 1986));
    System.out.println(selection);
  }
  public static void afficherElements(TreeSet ens){
    Iterator valeurs = ens.iterator();
    while(valeurs.hasNext()){
      System.out.println(valeurs.next());
    }
  }
}
