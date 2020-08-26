package grafosprojeto;

import grafosprojeto.objetos.Grafo;
import grafosprojeto.objetos.Vertice;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPrincipalController implements Initializable {

    Grafo g;
    
    @FXML
    private VBox pnPrincipal;
    @FXML
    private Pane pngrafo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = new Grafo(isDirecional());
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
    }
}