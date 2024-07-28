
package javafxapplication27;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class InstitutosController implements Initializable {
    
    @FXML
    private TableView<Instituto> tbInstituto;
    @FXML
    private TableColumn<Instituto, Integer> colIdInstituto;
    @FXML
    private TableColumn<Instituto, String> colNombreInstituto;
    @FXML
    private Label lblNombreInstituto;
    @FXML
    private TextField txtNombreInstituto;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVer;
    @FXML
    private TextField txtEliminar;
    
    private ObservableList<Instituto> listaInstitutos; 
    @FXML
    private Label lbIdInstituto;
    @FXML
    private TextField txtIdInstituto;
    
    DAOClass claseDAO = new DAOClass();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listaInstitutos = FXCollections.observableArrayList();
        
        colIdInstituto.setCellValueFactory(new PropertyValueFactory<>("idInstituto"));
        colNombreInstituto.setCellValueFactory(new PropertyValueFactory<>("nombreInstituto"));
        
        btnModificar.setDisable(true);

        tbInstituto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetallesInstituto(newSelection);
                btnModificar.setDisable(false);
            } else {
                btnModificar.setDisable(true);
            }
        });
    }  
    
     private void mostrarDetallesInstituto(Instituto instituto) {
        txtIdInstituto.setText(String.valueOf(instituto.getIdInstituto()));
        txtNombreInstituto.setText(instituto.getNombreInstituto());
    }

    @FXML
    private void crearInstituto(ActionEvent event) {
        String nombre = txtNombreInstituto.getText();
        Instituto insti = new Instituto (nombre);
        
        try {
            claseDAO.insertInstituto(insti);
        } catch (Exception ex) {
            Logger.getLogger(InstitutosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarInstituto(Instituto instituto) {
        instituto.setNombreInstituto(txtNombreInstituto.getText());
    }
    
    private void guardarCambiosEnBaseDeDatos(Instituto instituto) {
        try {
            claseDAO.actualizarInstituto(instituto);
            mostrarMensaje("Éxito", "Instituto actualizado", "Los datos del instituto se han actualizado correctamente.", AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al actualizar", "No se pudo actualizar el docente: " + e.getMessage(), AlertType.ERROR);
        }
    }
    
     private void actualizarTableView() {
        int selectedIndex = tbInstituto.getSelectionModel().getSelectedIndex();
        cargarDatosDeBaseDeDatos();
        tbInstituto.getSelectionModel().select(selectedIndex);
    }
     
    private void mostrarMensaje(String titulo, String cabecera, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void modificarInstituto(ActionEvent event) {
        Instituto institutoSeleccionado = tbInstituto.getSelectionModel().getSelectedItem();
        if (institutoSeleccionado != null){
            actualizarInstituto(institutoSeleccionado);
            guardarCambiosEnBaseDeDatos(institutoSeleccionado);
            actualizarTableView();
        }
    }

    @FXML
    private void eliminarInstitutos(ActionEvent event) {
        int id = Integer.parseInt(txtEliminar.getText());
        try {
            claseDAO.eliminarInstituto(id);
        } catch (Exception ex) {
            Logger.getLogger(InstitutosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarDatosDeBaseDeDatos() {
        try {
            List<Instituto> institutos = claseDAO.getAllInstitutos();
            listaInstitutos.clear();
            listaInstitutos.addAll(institutos);
            tbInstituto.setItems(listaInstitutos);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar los institutos");
            alert.setContentText("No se pudieron cargar los institutos: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void VerInstitutos(ActionEvent event) {
        cargarDatosDeBaseDeDatos();
    }
    
}
