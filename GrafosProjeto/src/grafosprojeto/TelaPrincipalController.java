package grafosprojeto;

import grafosprojeto.objetos.Grafo;
import grafosprojeto.objetos.Vertice;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class TelaPrincipalController implements Initializable {

    Grafo g;
    char cont;
    
    @FXML
    private VBox pnPrincipal;
    @FXML
    private Pane pngrafo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = new Grafo(isDirecional());
        cont = 64;
    }      
    private boolean isDirecional()
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
        a.setTitle("Direcional?");
        a.setContentText("É um grafo direcional?");
        if(a.showAndWait().get() == ButtonType.YES)
            return true;
        return false;
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
        
        if(vdd)
        {
            g.getVlist().add(v);
            mouseEvents(v);
            v.setID(++cont);
            Label l = new Label();
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
    
    private void criaVertice(String id1,String id2)
    {
        
    }
}