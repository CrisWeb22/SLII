
package javafxapplication27;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Cristian
 * --
 * Parent root = FXMLLoader.load(getClass().getResource("/vistas/Despedida.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        
        stage.show();
        * --
 */
public class IndexController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnDocente;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void IngresoDocente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/Docentes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
