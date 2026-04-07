package edu.epsevg.prop.ac1.cerca.heuristica;

import edu.epsevg.prop.ac1.model.Mapa;
import edu.epsevg.prop.ac1.model.Posicio;

import java.util.*;

/**
 * Heuristica avançada: Al vostre gust ;-)
 */

/*
 * L'heurística fa una estimació optimista del cost per arribar a la solució, 
 * assignant les claus als agents de forma col·laborativa.
 * 
*/
public class HeuristicaAvancada implements Heuristica {
    
    @Override
    public int h(Mapa estat, TreeMap<Character, Posicio> claus, Posicio[] portes) {

        List<Posicio> posAgents = estat.getAgents();
        int totalDistancia = 0;
        int clausMask = estat.getClausMask();
        Posicio meta = estat.getSortidaPosicio();

        TreeMap<Character, Posicio> clausPendents = new TreeMap<>();
        int index = 0;
        for (Map.Entry<Character, Posicio> entry : claus.entrySet()) {
            if ((clausMask & (1 << index)) == 0) { // mirar les claus
                clausPendents.put(entry.getKey(), entry.getValue());
            }
            index++;
        }

        Posicio[] posActuals = posAgents.toArray(new Posicio[0]);

        while (!clausPendents.isEmpty()) { // hi ha claus?
            int minDistancia = Integer.MAX_VALUE;
            int millorAgent = -1;
            Character clauMesProper = null;

            for (Map.Entry<Character, Posicio> entry : clausPendents.entrySet()) {
                Posicio posClau = entry.getValue();
                
                for (int i = 0; i < posActuals.length; i++) {
                    int dist = manhattan(posActuals[i], posClau);
                    
                    if (dist < minDistancia) {
                        minDistancia = dist;
                        millorAgent = i;
                        clauMesProper = entry.getKey();
                    }
                }
            }

            totalDistancia += minDistancia;

            posActuals[millorAgent] = clausPendents.get(clauMesProper);

            clausPendents.remove(clauMesProper);
        }

        int minSortida = Integer.MAX_VALUE;
        for (Posicio pos : posActuals) {
            int dist = manhattan(pos, meta);
            minSortida = Math.min(minSortida, dist);
        }

        totalDistancia += minSortida;

        return totalDistancia;
    }

    private int manhattan(Posicio a, Posicio b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}