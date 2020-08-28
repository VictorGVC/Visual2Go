package grafosprojeto.objetos;

import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Vertice {
    
    private Circle bola, dist;
    private int grau, cx, cy;
    private char ID, status;

    public Vertice() {
        status = 'c';
    }
    
    public Vertice(int x, int y, Pane p) {
        status = 'c';
        cx = x;
        cy = y;
        dist = new Circle(x,y,30);
        bola = new Circle(x,y,10);
        bola.setFill(Paint.valueOf("0x00FFFF"));
        bola.setStroke(Paint.valueOf("0x000000"));
        dist.setVisible(false);
        p.getChildren().add(dist);
        p.getChildren().add(bola);
        
    }

    public Circle getDist() {
        return dist;
    }

    public void setDist(Circle dist) {
        this.dist = dist;
    }
    
    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public Circle getBola() {
        return bola;
    }

    public void setBola(Circle bola) {
        this.bola = bola;
    }

    public char getID() {
        return ID;
    }

    public void setID(char ID) {
        this.ID = ID;
        this.bola.setId(""+ID);
    }
}