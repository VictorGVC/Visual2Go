package grafosprojeto.objetos;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Vertice {
    
    private Label l;
    private AnchorPane ap;
    private Circle bola, dist;
    private int grau, cx, cy;
    private char ID, status;

    public Vertice() {
        status = 'c';
    }
    
    public Vertice(int x, int y, Pane p,char ID) {
        l = new Label();
        l.setLayoutX(6);
        l.setLayoutY(2);
        l.setText(""+ID);
        
        ap = new AnchorPane();
        ap.setMinSize(0, 0);
        ap.setMaxSize(20, 20);
        ap.setLayoutX(x-10);
        ap.setLayoutY(y-10);
        
        status = 'c';
        cx = x;
        cy = y;
        dist = new Circle(x,y,30);
        dist.setVisible(false);
        bola = new Circle(10,10,10);
        bola.setFill(Paint.valueOf("0x00FFFF"));
        bola.setStroke(Paint.valueOf("0x000000"));
        
        p.getChildren().add(dist);
        ap.getChildren().addAll(bola,l);
        p.getChildren().add(ap);
    }

    public Label getL() {
        return l;
    }

    public void setL(Label l) {
        this.l = l;
    }

    public AnchorPane getAp() {
        return ap;
    }

    public void setAp(AnchorPane ap) {
        this.ap = ap;
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