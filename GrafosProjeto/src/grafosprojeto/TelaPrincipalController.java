package grafosprojeto;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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
    private JFXTextArea taLista;
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
                        else
                            gpmatrizadj.setVisible(false);
                            gpmatrizinc.setVisible(false);
                            taLista.setVisible(false);
                        break;
                    case 1:
                        gpmatrizadj.setVisible(false);
                        gpmatrizinc.setVisible(true);
                        taLista.setVisible(false);
                        break;
                    case 2:
                        gpmatrizadj.setVisible(false);
                        gpmatrizinc.setVisible(false);
                        taLista.setVisible(true);
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
    
    private void atualizaGpMa(){
        
        int [][] mat = g.getMa();
        
        gpmatrizadj.getChildren().clear();
        for(int i = 0; i < g.getVlist().size(); i++) 
        {
            gpmatrizadj.add(new Label(""+(char)(i+65)),i+1,0);
            gpmatrizadj.add(new Label(""+(char)(i+65)),0,i+1);
            for (int j = 0; j < g.getVlist().size(); j++) 
            {
                gpmatrizadj.add(new Label(""+mat[j][i]),i+1,j+1);
            }
        }  
    }
    
    
    private void atualizaGpMi(Aresta a) {
        
        List<ArrayList> mi = g.getMi();
        Label l = new Label();
        
        l.setPrefSize(30, 5);
        l.setText(a.getID1()+","+a.getID2());
        gpmatrizinc.add(l,g.getAlist().size(),0);
        
        for (int i = 0; i < g.getAlist().size(); i++)
            for (int j = 0; j < g.getVlist().size(); j++)
                gpmatrizinc.add(new Label("" + mi.get(i).get(j)),i+1,j+1);
    }
    
    private void atualizaLista() {
        
        String linha = "";
        List<ArrayList<Vertice>> list = g.getLa();
        
        for (int i = 0 ; i < g.getVlist().size() ; i++) {
            
            linha += g.getVlist().get(i).getID();
            for (int j = 0 ; j < list.get(i).size() ; j++) {
                if(j == 0)
                    linha += " -> ";
                linha += list.get(i).get(j).getID();
                if(j < list.get(i).size() - 1)
                    linha += " -> ";
            }
            linha += "\n\n";
        }
        taLista.setText(linha);
    }
    
    private void atualizaRep(Aresta a) {
        
        atualizaGpMa();
        atualizaGpMi(a);
        atualizaLista();
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
        atualizaGpMa();
        
        l3.setText(""+id);
        gpmatrizinc.add(l3,0,g.getVlist().size());
        atualizaLista();
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
        atualizaRep(a);
        if(g.isMultigrafo())
            gpmatrizadj.setVisible(false);
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