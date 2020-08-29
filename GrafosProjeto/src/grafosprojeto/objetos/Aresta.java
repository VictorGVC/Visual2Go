package grafosprojeto.objetos;

import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Aresta {
    
    private Line linha;
    private int ID, valor;
    private char ID1, ID2;

    public Aresta(int ID, Vertice v1, Vertice v2,char d,Pane p) {
        int v1x,v2x,v1y,v2y;
        this.ID = ID;
        this.ID1 = v1.getID();
        this.ID2 = v2.getID();
        if(d == 'd')
        {
            
        }
        else
        {   
            this.linha = new Line(v1.getCx(), v1.getCy(),v2.getCx(), v2.getCy());
            p.getChildren().add(linha);
            ObservableList<Node> aoba = FXCollections.observableArrayList(p.getChildren());
            Comparator<Node> c = Comparator.comparing(Node::getTypeSelector);
            Collections.sort(aoba,c.reversed());
            p.getChildren().setAll(aoba);
        }  
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public Line getLinha() {
        return linha;
    }

    public void setLinha(Line aresta) {
        this.linha = aresta;
    }

    public char getID1() {
        return ID1;
    }

    public void setID1(char ID1) {
        this.ID1 = ID1;
    }

    public char getID2() {
        return ID2;
    }

    public void setID2(char ID2) {
        this.ID2 = ID2;
    }
}