package grafosprojeto1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPrincipalController implements Initializable {

    @FXML
    private VBox pnPrincipal;
    @FXML
    private Canvas pnGrafo;
    @FXML
    private HBox pnMatrizes;
    @FXML
    private Pane pnMatrizAdj;
    @FXML
    private Pane pnMatrizInc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }      
}