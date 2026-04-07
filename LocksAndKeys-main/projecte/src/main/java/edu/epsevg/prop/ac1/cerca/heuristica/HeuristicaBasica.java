package edu.epsevg.prop.ac1.cerca.heuristica;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.epsevg.prop.ac1.model.Mapa;
import edu.epsevg.prop.ac1.model.Posicio;

/** 
 * Distància de Manhattan a la clau més propera 
 * (si queden per recollir) o a la sortida.
 */
public class HeuristicaBasica implements Heuristica {
    @Override
    public int h(Mapa estat, TreeMap<Character, Posicio> claus, Posicio[] portes) {
        //@TODO: reemplaceu tot el codi per la vostra heurística.

        Integer distanciaMesPropera = Integer.MAX_VALUE;
        List<Posicio> posAgents = estat.getAgents();
        Posicio meta = estat.getSortidaPosicio();

        if (estat.getClausMask() == (1 << claus.size()) - 1) { // si això es compleix s'han agafat totes les claus 
            // mirar meta
            for ( int i = 0; i < posAgents.size(); i++ ){

                Integer distancia = Math.abs(posAgents.get(i).x - meta.x) + Math.abs(posAgents.get(i).y - meta.y);
                if ( distancia < distanciaMesPropera ){
                    distanciaMesPropera = distancia;    
                }
            }
        } else { // si falten claus 
            for (int i = 0; i < posAgents.size(); i++) {
                for (Map.Entry<Character, Posicio> nodeArbre : claus.entrySet()) {
                    Posicio posClau = nodeArbre.getValue();
                    Integer distancia = Math.abs(posAgents.get(i).x - posClau.x) + Math.abs(posAgents.get(i).y - posClau.y);
                    if (distancia < distanciaMesPropera) {
                        distanciaMesPropera = distancia;    
                    }
                }
            }
        }
        return distanciaMesPropera;
    }
}
