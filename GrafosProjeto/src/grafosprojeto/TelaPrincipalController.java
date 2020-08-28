package grafosprojeto;

import grafosprojeto.objetos.Grafo;
import grafosprojeto.objetos.Vertice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class TelaPrincipalController implements Initializable {

    Grafo g;
    char cont;
    int qtdeV = 0;
    
    @FXML
    private VBox pnPrincipal;
    @FXML
    private Pane pngrafo;
    @FXML
    private GridPane gpmatrizinc;
    @FXML
    private GridPane gpmatrizadj;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        g = new Grafo(isDirecional());
        cont = 64;
        inicializaGp();
    }    
    
    private void inicializaGp(){
        
        gpmatrizadj.setMinSize(325, 185);
        gpmatrizadj.setVgap(2); 
        gpmatrizadj.setHgap(2);
        gpmatrizadj.setAlignment(Pos.CENTER_RIGHT);
        
        gpmatrizinc.setMinSize(325, 185);
        gpmatrizadj.setVgap(2); 
        gpmatrizinc.setHgap(2);
        gpmatrizinc.setAlignment(Pos.CENTER_LEFT);
    }
    
    private boolean isDirecional()
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        
        a.getButtonTypes().setAll(btsim,btnao);
        a.setTitle("Direcional?");
        a.setContentText("É um grafo direcional?");
        if(a.showAndWait().get() == btsim)
            return true;
        return false;
    }
    
    private void mouseEvents(Vertice v)
    {
        EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                v.getBola().setCursor(Cursor.HAND);
            }
        };
        
        EventHandler<MouseEvent> clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Circle o = (Circle) event.getSource();
                if(g.getStatus().isEmpty())
                    g.setStatus("s/" + o.getId());
                else
                {
                    String[] s = g.getStatus().split("/");
                    criaVertice(s[1],o.getId());
                }
            }
        };
        
        v.getBola().addEventHandler(MouseEvent.MOUSE_ENTERED, hover);
        v.getBola().addEventHandler(MouseEvent.MOUSE_CLICKED, clicked);
        
    }
    
    private void lMouseEvents(Label l,Vertice v)
    {
        EventHandler<MouseEvent> clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(g.getStatus().isEmpty())
                {
                    g.setStatus("s/" + v.getID());
                }
                else
                {
                    String[] s = g.getStatus().split("/");
                    criaVertice(s[1],""+v.getID());
                }
            }
        };
        
        EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                l.setCursor(Cursor.HAND);
            }
        };
        l.addEventHandler(MouseEvent.MOUSE_ENTERED, hover);
        l.addEventHandler(MouseEvent.MOUSE_CLICKED, clicked);
    }
    
    private void criaVertice(String id1, String id2)
    {
        
    }
    
    private void atualizaMatriz(char id){
        
        gpmatrizadj.add(new Label(""+id),qtdeV,0);
        gpmatrizadj.add(new Label(""+id),0,qtdeV);
        
        gpmatrizinc.add(new Label(""+id),0,qtdeV);
    }

    @FXML
    private void addVertice(MouseEvent event) 
    {
        boolean vdd = true;
        Vertice v = new Vertice((int)event.getX(),(int)event.getY(),pngrafo);
        
        for (Vertice vv : g.getVlist()) 
        {
            if(vv.getDist().getBoundsInParent().intersects(v.getBola().getBoundsInParent()))
                vdd = false;
        }
        if(vdd && qtdeV < 10)
        {
            Label l = new Label();
            
            g.getVlist().add(v);
            mouseEvents(v);
            v.setID(++cont);
            qtdeV++;
            atualizaMatriz(cont);
            l.setText(""+cont);
            l.setLayoutX(event.getX()-4);
            l.setLayoutY(event.getY()-9);
            pngrafo.getChildren().add(l);
            lMouseEvents(l,v);
        }
        else
        {
            pngrafo.getChildren().removeAll(v.getBola(),v.getDist());
            v = null;
        }
    }
}