package grafosprojeto.objetos;

import javafx.scene.shape.Line;

public class Aresta {
    
    private Line aresta;
    private int ID1, ID2, valor;

    public Line getAresta() {
        return aresta;
    }

    public void setAresta(Line aresta) {
        this.aresta = aresta;
    }

    public int getID1() {
        return ID1;
    }

    public void setID1(int ID1) {
        this.ID1 = ID1;
    }

    public int getID2() {
        return ID2;
    }

    public void setID2(int ID2) {
        this.ID2 = ID2;
    }
}