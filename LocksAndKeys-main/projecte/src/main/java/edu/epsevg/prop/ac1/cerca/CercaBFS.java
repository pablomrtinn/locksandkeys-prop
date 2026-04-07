package edu.epsevg.prop.ac1.cerca;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import edu.epsevg.prop.ac1.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

public class CercaBFS extends CercaDopada {
    public CercaBFS(boolean usarLNT) { super(usarLNT); } // Super: CercaBFS es fill d' una Cerca(Pare). 
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        ArrayDeque<Node> LNO = new ArrayDeque<>();
        Map<Mapa, Node> LNT = new HashMap<>(); // guardem Taulell i alçada 
        Node na = new Node(inicial, null, null, 0, 0);
        LNO.push(na);
        boolean hiHaSolucio = false;
        
        while(!LNO.isEmpty()){
            na = LNO.removeFirst();
            rc.incNodesExplorats();
            if(na.estat.esMeta()){
                hiHaSolucio = true;
                break;
            } 
            else{
                boolean expandir = tractarLNTDepth(na, LNT, rc);
                
                if (expandir) {
                    for (Moviment m : na.estat.getAccionsPossibles()){
                        Node fill = new Node(na.estat.mou(m), na, m, na.depth + 1, 0); 
                        LNO.addLast(fill);
                    }
                }
            }
            rc.updateMemoria(LNO.size() + LNT.size());
        }
        if(hiHaSolucio) rc.setCami(reconstruirCami(na));
    }
}