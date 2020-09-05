package grafosprojeto;

import com.jfoenix.controls.JFXComboBox;
import grafosprojeto.objetos.Aresta;
import grafosprojeto.objetos.Grafo;
import grafosprojeto.objetos.Vertice;
import grafosprojeto.utils.util;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import javax.swing.JOptionPane;

public class TelaPrincipalController implements Initializable {

    Grafo g;
    char cont;
    int qtdeInc, conta;
    
    @FXML
    private VBox pnPrincipal;
    @FXML
    private Pane pngrafo;
    @FXML
    private JFXComboBox<String> cbMatrizes;
    private Label lbQtdes;
    @FXML
    private Label lbSimples;
    @FXML
    private Label lbRegular;
    @FXML
    private Label lbCompleto;
    @FXML
    private Label lbMultigrafo;
    @FXML
    private GridPane gpmatrizinc;
    @FXML
    private GridPane gpmatrizadj;
    @FXML
    private Label lbQtdeV;
    @FXML
    private Label lbQtdeA;

    @Override  
    public void initialize(URL url, ResourceBundle rb) 
    {
        g = new Grafo(isDirecional(),isValorado());
        cont = 64;
        inicializaGp();
        conta = 0;
        qtdeInc = 1;
        listaMatrizaes();
        esperaLista();
    }    
    
    private void listaMatrizaes() {
        
        List<String> list = new ArrayList();
        
        list.add("Matriz de Adjacência");
        list.add("Matriz de Incidência");
        list.add("Lista de Adjacência");
        
        cbMatrizes.setItems(FXCollections.observableArrayList(list));
    }
    
    private void esperaLista(){
        
        cbMatrizes.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                switch (cbMatrizes.getSelectionModel().getSelectedIndex()) {

                    case 0:
                        gpmatrizadj.setVisible(true);
                        gpmatrizinc.setVisible(false);
                        break;
                    case 1:
                        gpmatrizadj.setVisible(false);
                        gpmatrizinc.setVisible(true);
                        break;
                    case 2:
                        gpmatrizadj.setVisible(false);
                        gpmatrizinc.setVisible(false);
                        break;   
                    default:
                        break;
                }
            }
        });
    }
    public boolean isValorado()
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        
        a.getButtonTypes().setAll(btsim,btnao);
        a.setTitle("Valorado?");
        a.setContentText("É um grafo Valorado?");
        if(a.showAndWait().get() == btsim)
            return true;
        return false;
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
        g.getMi().add(new ArrayList());
        Vertice v1 = null, v2 = null;
        int valor = 1;
        Aresta a = null;  
        
        for (Vertice v : g.getVlist()) 
        {
            if(v.getID() == id1)
                v1 = v;
            if(v.getID() == id2)
                v2 = v;
        }
        
        if(g.isVal())
        {
            valor = Integer.parseInt(JOptionPane.showInputDialog("valor"));
            String s = util.pontoMedio(v1.getCx(), v1.getCy(), v2.getCx(), v2.getCy());
            Label l = new Label(""+valor);
            String []s1 = s.split("/");
            l.setLayoutX(Integer.parseInt(s1[0]));
            l.setLayoutY(Integer.parseInt(s1[1])+6);
            pngrafo.getChildren().add(l);
            if(v1 == v2)
                l.setLayoutY(Integer.parseInt(s1[1])+20);
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
            g.addMa(v1.getID()-65, v1.getID()-65, valor);
            g.addMi(v1.getID()-65, valor);
        }      
        else
        {
            a = new Aresta(conta++,v1,v2,g.isDir(),pngrafo);
            g.addMa(v1.getID()-65, v2.getID()-65, valor);
            g.addMi(v2.getID()-65, valor);
            if(!g.isDir())
            {
                g.addMa(v2.getID()-65, v1.getID()-65, valor);
                g.addMi(v1.getID()-65, valor);
            }
            else
                g.addMi(v1.getID()-65, -1*valor);
                
        }
        g.getAlist().add(a);
        lbQtdeA.setText("A = " + conta);    
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
            lbQtdeV.setText("V = " + (cont - 64));
        }
        else
        {
            cont--;
            pngrafo.getChildren().removeAll(v.getAp(),v.getDist());
            v = null;
        }
    }
}