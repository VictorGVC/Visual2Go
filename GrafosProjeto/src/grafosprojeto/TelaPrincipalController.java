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
import javafx.geometry.Pos;
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
    public void initialize(URL url, ResourceBundle rb) {
        
        g = new Grafo(isDirecional(),isValorado());
        cont = 64;
        inicializaGp();
        conta = 0;
        qtdeInc = 1;
        listaMatrizaes();
        esperaLista();
    }
    
    private boolean isDirecional() {
        
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
    
    public boolean isValorado() {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        
        a.getButtonTypes().setAll(btsim,btnao);
        a.setTitle("Valorado?");
        a.setContentText("É um grafo valorado?");
        if(a.showAndWait().get() == btsim)
            return true;
        return false;
    }    
    
    private void listaMatrizaes() {
        
        List<String> list = new ArrayList();
        
        list.add("Matriz de Adjacência");
        list.add("Matriz de Incidência");
        list.add("Lista de Adjacência");
        
        cbMatrizes.setItems(FXCollections.observableArrayList(list));
    }
    
    private void esperaLista() {
        
        cbMatrizes.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                switch (cbMatrizes.getSelectionModel().getSelectedIndex()) {

                    case 0:
                        if(!g.isMultigrafo())
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
    
    private void inicializaGp() {
        
        gpmatrizadj.setVgap(5); 
        gpmatrizadj.setHgap(5);
        gpmatrizadj.setAlignment(Pos.CENTER_RIGHT);
        
        gpmatrizinc.setVgap(5); 
        gpmatrizinc.setHgap(5);
        gpmatrizinc.setAlignment(Pos.CENTER_LEFT);
    }
    
    private void atualizaGpMa(Aresta a){
        
        int i, j, k ,l;
        int [][] mat = g.getMa();
        
        if(a == null)
            for (i = 1, k = 0 ; i <= g.getVlist().size() ; i++)
                for (j = 1, l = 0 ; j <= g.getVlist().size() ; j++) 
                    gpmatrizadj.add(new Label("" + mat[k][l]),j,i);
        else{
            
            Node n = null;
            
            i = a.getID2() - 64;
            j = a.getID1() - 64;
            k = i - 1;
            l = j - 1;
            
            for (Node node : gpmatrizadj.getChildren()) {
                if(node instanceof Label && gpmatrizadj.getRowIndex(node) == j && gpmatrizadj.getColumnIndex(node) == i)
                    n = node;
            }
            gpmatrizadj.getChildren().remove(n);
            for (Node node : gpmatrizadj.getChildren()) {
                if(node instanceof Label && gpmatrizadj.getRowIndex(node) == i && gpmatrizadj.getColumnIndex(node) == j)
                    n = node;
            }
            gpmatrizadj.getChildren().remove(n);
            
            gpmatrizadj.add(new Label("" + mat[k][l]),j,i);
            gpmatrizadj.add(new Label("" + mat[k][l]),i,j);
        }
    }
    
    private void atualizaGp(Aresta a) {
        
        atualizaGpMa(a);
        
        Label l = new Label();
        
        l.setPrefSize(30, 5);
        
        for (int i = 1; i <= g.getVlist().size(); i++)
            if(i != a.getID1()-64 && i != a.getID2()-64)
                gpmatrizinc.add(new Label(""+0),qtdeInc,i);
                
        l.setText(a.getID1()+","+a.getID2());
        gpmatrizinc.add(l,qtdeInc,0);
        gpmatrizinc.add(new Label(""+1),qtdeInc,a.getID1()-64);
        gpmatrizinc.add(new Label(""+1),qtdeInc++,a.getID2()-64);
    }
    
    private void addVerticeMatriz(char id) {
        
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
        atualizaGpMa(null);
        
        l3.setText(""+id);
        gpmatrizinc.add(l3,0,g.getVlist().size());
    }
    
    private void verificaTipo() {
        
        int i, j, qtdeV = cont - 64, grau;
        boolean flag = true;
        Aresta auxA, auxA2;
        
        lbSimples.setText("Simples? Sim");
        g.setSimples(true);
        lbMultigrafo.setText("Multigrafo? Não");
        g.setMultigrafo(false);
        for (i = 0 ; i < conta && flag; i++) {
            
            auxA = g.getAlist().get(i);
            for (j = i + 1 ; j < conta && flag; j++) {
                
                auxA2 = g.getAlist().get(j);
                if(auxA.getID1() == auxA2.getID2() && auxA.getID2() == auxA2.getID1()){
                    
                    lbSimples.setText("Simples? Não");
                    g.setSimples(false);
                    lbMultigrafo.setText("Multigrafo? Sim");
                    g.setMultigrafo(true);
                    flag = false;
                }
            }
        }
        
        flag = true;
        grau = g.getVlist().get(0).getGrau();
        for (i = 1 ; i < qtdeV && flag; i++) 
        {
            if(grau != g.getVlist().get(i).getGrau())
                flag = false;
        }
        if(flag)
        {
            lbRegular.setText("Regular? Sim");
            g.setRegular(true);
        } 
        else      
        {
            lbRegular.setText("Regular? Não");
            g.setRegular(false);
        }
        if(qtdeV * (qtdeV - 1) / 2 == conta)
        {
            lbCompleto.setText("Completo? Sim");
            g.setCompleto(true);
        }
        else
        {
            lbCompleto.setText("Completo? Não");
            g.setCompleto(false);
        }
            
    }
    
    private void mouseEvents(Vertice v) {
        
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
                else{
                    
                    String[] s = g.getStatus().split("/");
                    g.setStatus("");
                    criaAresta(s[1].charAt(0),v.getID());
                }
            }
        };
        v.getAp().addEventFilter(MouseEvent.MOUSE_ENTERED, hover);
        v.getAp().addEventFilter(MouseEvent.MOUSE_CLICKED, clicked);
    }
    
    private void criaAresta(char id1, char id2) {
        
        g.getMi().add(new ArrayList());
        Vertice v1 = null, v2 = null;
        int valor = 1;
        Aresta a = null;  
        
        for (Vertice v : g.getVlist()) {
            
            if(v.getID() == id1)
                v1 = v;
            if(v.getID() == id2)
                v2 = v;
        }
        
        v1.setGrau(v1.getGrau() + 1);
        v2.setGrau(v2.getGrau() + 1);
        if(g.isVal()){
            
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
        if(v1 == v2){
            
            v1.getLoop().setVisible(true);
            a = new Aresta(conta++, v1);
            if(g.isDir()){
                
                Line l1 = new Line(19, 16, 15, 23);
                Line l2 = new Line(19, 16, 24, 22);
                v1.getAp().getChildren().addAll(l2,l1);
            }
            g.addMa(v1.getID()-65, v1.getID()-65, valor);
            g.addMi(v1.getID()-65, valor);
            g.addLa(v1, v1.getID()-65);
        }      
        else{
            
            a = new Aresta(conta++,v1,v2,g.isDir(),pngrafo);
            g.addMa(v1.getID()-65, v2.getID()-65, valor);
            g.addMi(v2.getID()-65, valor);
            g.addLa(v2, v1.getID()-65);
            if(!g.isDir()){
                
                g.addMa(v2.getID()-65, v1.getID()-65, valor);
                g.addMi(v1.getID()-65, valor);
                g.addLa(v1, v2.getID()-65);
            }
            else
                g.addMi(v1.getID()-65, -1*valor);
                
        }
        g.getAlist().add(a);
        verificaTipo();
        lbQtdeA.setText("A = " + conta);
        atualizaGp(a);
    }

    @FXML
    private void addVertice(MouseEvent event) {
        
        boolean vdd = true;
        Vertice v = new Vertice((int)event.getX(),(int)event.getY(),pngrafo,++cont);
        
        for (Vertice vv : g.getVlist())
            if(vv.getDist().getBoundsInParent().intersects(v.getAp().getBoundsInParent()))
                vdd = false;
        if(vdd && g.getVlist().size() < 10){
            
            g.getVlist().add(v);
            mouseEvents(v);
            v.setID(cont);
            verificaTipo();
            lbQtdeV.setText("V = " + (cont - 64));
            addVerticeMatriz(cont);
        }
        else{
            
            cont--;
            pngrafo.getChildren().removeAll(v.getAp(),v.getDist());
            v = null;
        }
    }
}