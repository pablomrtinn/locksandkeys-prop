package edu.epsevg.prop.ac1.cerca.heuristica;

import java.util.TreeMap;

import edu.epsevg.prop.ac1.model.Mapa;
import edu.epsevg.prop.ac1.model.Posicio;

/**
 * Interfície per totes les heurístiques
 */
public interface Heuristica {
    /**
     * Retorna el valor heurístic que li correspon al mapa
     * @param estat és el mapa que volem avaluar
     */
    int h(Mapa estat, TreeMap<Character,Posicio> claus, Posicio[] portes);
}
