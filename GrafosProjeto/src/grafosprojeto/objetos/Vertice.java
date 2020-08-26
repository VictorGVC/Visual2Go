/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosprojeto.objetos;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author vicga
 */
public class Vertice {
    private Circle bola;
    private int ID,grau,cx,cy;

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Vertice() {
    }
    
    public Vertice(int x, int y, Pane p) {
        bola = new Circle(cx,cy,10);
        bola.setCenterX(x);
        bola.setCenterY(y);
        bola.setFill(Paint.valueOf("0x0000FF"));
        bola.setStroke(Paint.valueOf("0x000000"));
        
        p.getChildren().add(bola);
    }
}
