package grafosprojeto;

import grafosprojeto.objetos.Aresta;
import grafosprojeto.objetos.Grafo;
import grafosprojeto.objetos.Vertice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class TelaPrincipalController implements Initializable {

    Grafo g;
    char cont;
    int qtdeInc, conta;
    
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
        conta = 0;
        qtdeInc = 1;
    }    
    
    private void inicializaGp(){
        
        //gpmatrizadj.setPrefSize(325, 185);
        //gpmatrizadj.setMinSize(325, 185);
        gpmatrizadj.setVgap(5); 
        gpmatrizadj.setHgap(5);
        //gpmatrizadj.setAlignment(Pos.CENTER_RIGHT);
        
        //gpmatrizinc.setMinSize(325, 185);
        gpmatrizinc.setVgap(5); 
        gpmatrizinc.setHgap(5);
        //gpmatrizinc.setAlignment(Pos.CENTER_LEFT);
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
                v.getAp().setCursor(Cursor.HAND);
            }
        };
        
        EventHandler<MouseEvent> clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                v.getAp().requestFocus();
                
                if(g.getStatus().isEmpty())
                    g.setStatus("s/" + v.getID());
                else
                {
                    String[] s = g.getStatus().split("/");
                    g.setStatus("");
                    criaAresta(s[1].charAt(0),v.getID());
                }
            }
        };
        v.getAp().addEventFilter(MouseEvent.MOUSE_ENTERED, hover);
        v.getAp().addEventFilter(MouseEvent.MOUSE_CLICKED, clicked);
    }
    
    private void criaAresta(char id1, char id2)
    {
        Vertice v1 = null, v2 = null;
        
        Aresta a = null;
        
        for (Vertice v : g.getVlist()) 
        {
            if(v.getID() == id1)
                v1 = v;
            if(v.getID() == id2)
                v2 = v;
        }
        if(v1 == v2)
        {
            v1.getLoop().setVisible(true);
            a = new Aresta(conta++, v1);
            if(g.isDir())
            {
                Line l1 = new Line(19, 16, 15, 23);
                Line l2 = new Line(19, 16, 24, 22);
                v1.getAp().getChildren().addAll(l2,l1);
            }
        }      
        else
            a = new Aresta(conta++,v1,v2,g.isDir(),pngrafo);
        atualizaMatriz(a);
    }
    
    private void atualizaMatriz(Aresta a){
        
        Node n = null;
        Label l = new Label();
        
        l.setPrefSize(30, 5);
        
        for (Node node : gpmatrizadj.getChildren()) {
            if(node instanceof Label && gpmatrizadj.getRowIndex(node) == a.getID1()-64 && gpmatrizadj.getColumnIndex(node) == a.getID2()-64)
                n = node;
        }
        gpmatrizadj.getChildren().remove(n);
        for (Node node : gpmatrizadj.getChildren()) {
            if(node instanceof Label && gpmatrizadj.getRowIndex(node) == a.getID2()-64 && gpmatrizadj.getColumnIndex(node) == a.getID1()-64)
                n = node;
        }
        gpmatrizadj.getChildren().remove(n);
        
        gpmatrizadj.add(new Label(""+1),a.getID1()-64,a.getID2()-64);
        gpmatrizadj.add(new Label(""+1),a.getID2()-64,a.getID1()-64);
        
        for (int i = 1; i <= g.getVlist().size(); i++)
            if(i != a.getID1()-64 && i != a.getID2()-64)
                gpmatrizinc.add(new Label(""+0),qtdeInc,i);
                
        l.setText(a.getID1()+","+a.getID2());
        gpmatrizinc.add(l,qtdeInc,0);
        gpmatrizinc.add(new Label(""+1),qtdeInc,a.getID1()-64);
        gpmatrizinc.add(new Label(""+1),qtdeInc++,a.getID2()-64);
    }
    
    private void atualizaMatriz(char id){
        
        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        
        l1.setPrefSize(30, 5);
        l2.setPrefSize(30, 5);
        l3.setPrefSize(30, 5);
        
        l1.setText(""+id);
        l2.setText(""+id);
        gpmatrizadj.add(l1,g.getVlist().size(),0);
        gpmatrizadj.add(l2,0,g.getVlist().size());
        
        for (int i = 1; i <= g.getVlist().size(); i++)
            gpmatrizadj.add(new Label(""+0),g.getVlist().size(),i);
        for (int i = 1; i <= g.getVlist().size(); i++)
            gpmatrizadj.add(new Label(""+0),i,g.getVlist().size());
        
        l3.setText(""+id);
        gpmatrizinc.add(l3,0,g.getVlist().size());
    }

    @FXML
    private void addVertice(MouseEvent event) 
    {
        boolean vdd = true;
        Vertice v = new Vertice((int)event.getX(),(int)event.getY(),pngrafo,++cont);
        
        for (Vertice vv : g.getVlist()) 
        {
            if(vv.getDist().getBoundsInParent().intersects(v.getAp().getBoundsInParent()))
                vdd = false;
        }
        if(vdd && g.getVlist().size() < 10)
        {
            g.getVlist().add(v);
            mouseEvents(v);
            v.setID(cont);
            atualizaMatriz(cont);
        }
        else
        {
            cont--;
            pngrafo.getChildren().removeAll(v.getAp(),v.getDist());
            v = null;
        }
    }
}