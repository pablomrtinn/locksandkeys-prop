package edu.epsevg.prop.ac1.cerca;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import edu.epsevg.prop.ac1.cerca.heuristica.Heuristica;
import edu.epsevg.prop.ac1.model.*;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;


// @TODO
public class CercaAStar extends CercaDopada {

    private final Heuristica heur;
    
    public CercaAStar(boolean usarLNT, Heuristica heur) { //li hem de pasar la heuristica 
        super(usarLNT); 
        this.heur = heur;
    }

    // precalculs 
    private void preCalculs(Mapa inicial, TreeMap<Character,Posicio> claus, Posicio[] portes) {
        int grid[][] = inicial.getGrid();
        for (int i = 0; i < inicial.getN(); ++i) {
            for (int j = 0; j < inicial.getM(); j++) {
                if (grid[i][j] >= 'a' && grid[i][j] <= 'z') {
                    Posicio pos = new Posicio(i, j);
                    claus.put((char)grid[i][j], pos);
                }
                else if (grid[i][j] >= 'A' && grid[i][j] <= 'Z') {
                    portes[grid[i][j] - 'A'] = new Posicio(i, j);
                }
            }
        }
    }

    @Override
    public  void ferCerca(Mapa inicial, ResultatCerca rc) {
        // Precalculem paràmetres abans de fes cap cerca
        TreeMap<Character, Posicio> treeMapClaus = new TreeMap<>();
        Posicio[] portes = new Posicio[26];
        Posicio meta = inicial.getSortidaPosicio();
        preCalculs(inicial, treeMapClaus, portes);

        //----------------------------------------------------------------------------------

        PriorityQueue<NodeExtended> LNO = new PriorityQueue<>(
            Comparator.comparingInt(NodeExtended::getCostTotal)
        );
        Map<Mapa, NodeExtended> LNT = new HashMap<>(); // guardem Taulell i alçada 
        NodeExtended na = new NodeExtended(inicial, null, null, 0, 0, 0);
        LNO.add(na);
        boolean hiHaSolucio = false;
        
        while(!LNO.isEmpty()){
            na = LNO.remove();
            rc.incNodesExplorats();
            if(na.estat.esMeta()){
                hiHaSolucio = true;
                break;
            } 
            else{
                boolean expandir = tractarLNTCostHeuristica(na, LNT, rc);
                
                if (expandir) {
                    for (Moviment m : na.estat.getAccionsPossibles()){
                        Mapa nouEstat = na.estat.mou(m);
                        int nouG = na.g + 1; // Cost acumulat
                        int heu = heur.h(nouEstat, treeMapClaus, portes);
                        int nouF = nouG + heu;
                        NodeExtended fill = new NodeExtended(na.estat.mou(m), na, m, na.depth + 1, na.g + 1, nouF);
                        LNO.add(fill);
                    }
                }
            }
            rc.updateMemoria(LNO.size() + LNT.size());
        }
        if(hiHaSolucio) rc.setCami(reconstruirCami(na));
    }     
}


