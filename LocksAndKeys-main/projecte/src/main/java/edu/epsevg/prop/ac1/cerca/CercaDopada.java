package edu.epsevg.prop.ac1.cerca;

import java.util.LinkedList;
import java.util.Map;

import edu.epsevg.prop.ac1.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;


public abstract class CercaDopada extends Cerca {
    public CercaDopada(boolean usarLNT) {
        super(usarLNT);
    }

    public LinkedList<Moviment> reconstruirCami(Node node) {
        LinkedList<Moviment> cami = new LinkedList<>();
        while (node.pare != null) {
            cami.addFirst(node.accio);
            node = node.pare;
        }
        return cami;
    }

    public boolean hiHaCicle(Node na, Node antecessor) {
        boolean hi_ha_cicle = false;
        if (antecessor != null) {
            if (na.estat.equals(antecessor.estat)) {
                hi_ha_cicle = true;
            } else {
                hi_ha_cicle = hiHaCicle(na, antecessor.pare);
            }
        }
        return hi_ha_cicle;
    }

    public boolean tractarLNTDepth(Node na, Map<Mapa, Node> LNT, ResultatCerca rc){
        boolean expandir = true;
        if(usarLNT){
            Node node = LNT.get(na.estat);
            if(node == null || na.depth < node.depth){
                LNT.put(na.estat, na);
            } else {
                expandir = false;
                rc.incNodesTallats();
            }
        }
        else{
            if(hiHaCicle(na, na.pare)){
                expandir = false;
                rc.incNodesTallats();
            } 
        }
        return expandir;
    }

    public boolean tractarLNTCostHeuristica(NodeExtended na, Map<Mapa, NodeExtended> LNT, ResultatCerca rc){
        boolean expandir = true;
        if (usarLNT){
            NodeExtended node = LNT.get(na.estat); // li passem un mapa per a que ens retorni el node amb el = mapa
            if(node == null || na.getCostTotal() < node.getCostTotal()){
                LNT.put(na.estat, na);
            } else {
                expandir = false;
                rc.incNodesTallats();
            }
        }
        else{
            if(hiHaCicle(na, na.pare)){
                expandir = false;
                rc.incNodesTallats();
            } 
        }
        return expandir;
    }
}

