package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;

public class NodeExtended extends Node {
    
    private int costTotal;
    
    public NodeExtended(Mapa estat, Node pare, Moviment accio, int depth, int g, int costTotal ) {
        super(estat, pare, accio, depth, g);
        this.costTotal = costTotal;
    }

    public int getCostTotal(){
        return costTotal;
    }
}
