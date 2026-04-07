package edu.epsevg.prop.ac1.cerca;


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import edu.epsevg.prop.ac1.model.*;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;


public class CercaIDS extends CercaDopada {
    public CercaIDS(boolean usarLNT) { super(usarLNT); }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        boolean hiHaSolucio = false;
        Integer profunditat_max = 0;
        Node na = null;

        while(!hiHaSolucio && profunditat_max <= 50){
            ArrayDeque<Node> LNO = new ArrayDeque<>();
            Map<Mapa, Node> LNT = new HashMap<>(); // guardem Taulell i node 
            na = new Node(inicial, null, null, 0, 0);
            LNO.push(na);
            
            while(!LNO.isEmpty()){
                na = LNO.removeFirst();
                rc.incNodesExplorats();
                if(na.estat.esMeta()){
                    hiHaSolucio = true;
                    break;
                } 
                else{                    
                    boolean expandir = tractarLNTDepth(na, LNT, rc);
                    
                    if (expandir && na.depth < profunditat_max) { // Els fills tindràn depth 50
                        for (Moviment m : na.estat.getAccionsPossibles()){
                            Node fill = new Node(na.estat.mou(m), na, m, na.depth + 1, 0); 
                            LNO.addFirst(fill);
                        }
                    }
                }
                rc.updateMemoria(LNO.size() + LNT.size());
            }
            profunditat_max++;
        }
        if(hiHaSolucio) rc.setCami(reconstruirCami(na));
    }
}
