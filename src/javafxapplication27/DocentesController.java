
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.util.StringConverter;


public class DocentesController implements Initializable {

   
    @FXML
    private TextField txfID;
    @FXML
    private TextField txfNonmbre;
    @FXML
    private TextField txfApellido;
    @FXML
    private TextField txfDNI;
    @FXML
    private DatePicker dpFechaNac;
    @FXML
    private TextField txfDireccion;
    @FXML
    private TextField txfLegajo;
    @FXML
    private Label lbID;
    @FXML
    private Label lbNombreDocente;
    @FXML
    private Label lbApellidoDocente;
    @FXML
    private Label lbDNIDocente;
    @FXML
    private Label lbFechaNacimiento;
    @FXML
    private Label lbDireccion;
    @FXML
    private Label lbLegajo;
    
    @FXML
    private TableView<Docente> tbDocentes; //Tabla de clase Docente
    @FXML
    private TableColumn<Docente, Integer> colID; //columna ID
    @FXML
    private TableColumn<Docente, String> colNom; //columna nombre
    @FXML
    private TableColumn<Docente, String> colApellido; //columna apellido
    @FXML
    private TableColumn<Docente, Integer> colDNI; //columna DNI
    @FXML
    private TableColumn<Docente, String> colFechaNac; //columna fecha de nacimiento
    @FXML
    private TableColumn<Docente, String> colDireccion; // columna direccion
    @FXML
    private TableColumn<Docente, Integer> colLegajo; //columna legajo
    @FXML
    private TableColumn<Docente, Instituto> colInstituto; //columna instituto
    
    private ObservableList<Docente> listaDocentes; //Lista en la cual se guardaran los docentes de la BD para mostrar
    
    DAOClass claseDao = new DAOClass();
    
    @FXML
    private Button btnCrearDocente; //Boton CREAR
    @FXML
    private Button btnVerDocentes; //Boton VER
    @FXML
    private Button btnEliminarDocente; //Boton ELIMINAR
    @FXML
    private TextField txtEliminar;
    @FXML
    private Label lbEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private Label lbSeleccionarInstituto;
    @FXML
    private ComboBox<Instituto> comboInstituto;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        configurarComboBox();
        cargarInstitutosEnComboBox();
        
        listaDocentes = FXCollections.observableArrayList();
       
        colID.setCellValueFactory(new PropertyValueFactory<>("idDocente"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombreDocente"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoDocente"));
        colDNI.setCellValueFactory(new PropertyValueFactory<>("dniDocente"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNacimientoDocente"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionDocente"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<>("legajoDocente"));
        colInstituto.setCellValueFactory(new PropertyValueFactory<>("instituto"));
        colInstituto.setCellFactory(column -> {
            return new TableCell<Docente, Instituto>() {
                @Override
                protected void updateItem(Instituto item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getNombreInstituto());
                    }
                }
            };
        });
        
        
        btnModificar.setDisable(true);

        tbDocentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetallesDocente(newSelection);
                btnModificar.setDisable(false);
            } else {
                btnModificar.setDisable(true);
            }
        });
    }   
    
    private void configurarComboBox(){
        comboInstituto.setCellFactory((listView) -> new ListCell<Instituto>(){
            @Override
            protected void updateItem(Instituto item, boolean empty){
                super.updateItem(item, empty);
                if (item != null){
                    setText(item.getNombreInstituto());
                } else {
                    setText(null);
                }
            }
        });
        comboInstituto.setConverter(new StringConverter<Instituto>(){
            @Override
            public String toString(Instituto instituto){
                return instituto == null ? null : instituto.getNombreInstituto();
            }
            @Override
            public Instituto fromString(String string) {
                return null;
            }
        });
    }
    
    private void cargarInstitutosEnComboBox(){
        try{
            List<Instituto> institutos = claseDao.getAllInstitutos();
            ObservableList<Instituto> institutosObservable = convertirAObservableList(institutos);
            comboInstituto.setItems(institutosObservable);
        }catch(Exception e){
            mostrarMensajeError("Error al cargar los institutos: " + e.getMessage());
        }
    }
    
    private ObservableList<Instituto> convertirAObservableList(List<Instituto> institutos) {
        return FXCollections.observableArrayList(institutos);
    }
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void mostrarDetallesDocente(Docente docente) {
        txfID.setText(String.valueOf(docente.getIdDocente()));
        txfNonmbre.setText(docente.getNombreDocente());
        txfApellido.setText(docente.getApellidoDocente());
        txfDNI.setText(String.valueOf(docente.getDniDocente()));
        dpFechaNac.setValue(docente.getFechaNacimientoDocente());
        txfDireccion.setText(docente.getDireccionDocente());
        txfLegajo.setText(String.valueOf(docente.getLegajoDocente()));
    }
    
    private void limpiarCampos() {
    txfNonmbre.clear();
    txfApellido.clear();
    txfDNI.clear();
    dpFechaNac.setValue(null);
    txfDireccion.clear();
    txfLegajo.clear();
    comboInstituto.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void crearDocente(ActionEvent event) {
        String nombre = txfNonmbre.getText();
        String apellido = txfApellido.getText();
        int dni = Integer.parseInt(txfDNI.getText());
        LocalDate fecha = dpFechaNac.getValue();
        String direccion = txfDireccion.getText();
        int legajo = Integer.parseInt(txfLegajo.getText());
        Instituto instituto = comboInstituto.getValue();
        Docente doc = new Docente(nombre, apellido, dni, fecha, direccion, legajo, instituto);
        
        //ACA PODRIA IR VERIFICACION DE QUE NO ESTE EN BLANCO LA SELECCION.
        try {
            claseDao.insertDocente(doc);
        } catch (Exception ex) {
            Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiarCampos();
    }
    
    private void cargarDatosDeBaseDeDatos() {
        try {
            List<Docente> docentes = claseDao.getAllDocentes();
            /* COMENTADO PARA PROBAR SIN IMPRESION POR CONSOLA DE NETBEANS
            System.out.println("Docentes cargados: " + docentes.size());
            for (Docente docente : docentes) {
                System.out.println("Docente: " + docente.getNombreDocente() + ", Instituto: " + 
                    (docente.getInstituto() != null ? docente.getInstituto().getNombreInstituto() : "No asignado"));
            }*/
            listaDocentes.clear();
            listaDocentes.addAll(docentes);
            tbDocentes.setItems(listaDocentes);
            tbDocentes.refresh(); // Forzar actualización de la TableView
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar los docentes");
            alert.setContentText("No se pudieron cargar los docentes: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void verDocentes(ActionEvent event) {
        cargarDatosDeBaseDeDatos();
    }

    @FXML
    private void eliminarDocente(ActionEvent event) {
        int id = Integer.parseInt(txtEliminar.getText());
        try {
            claseDao.eliminarDocente(id);
        } catch (Exception ex) {
            Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarDocente(Docente docente) {
        docente.setNombreDocente(txfNonmbre.getText());
        docente.setApellidoDocente(txfApellido.getText());
        docente.setDniDocente(Integer.parseInt(txfDNI.getText()));
        docente.setFechaNacimientoDocente(dpFechaNac.getValue());
        docente.setDireccionDocente(txfDireccion.getText());
        docente.setLegajoDocente(Integer.parseInt(txfLegajo.getText()));
    }
    
    private void guardarCambiosEnBaseDeDatos(Docente docente) {
        try {
            claseDao.actualizarDocente(docente);
            mostrarMensaje("Éxito", "Docente actualizado", "Los datos del docente se han actualizado correctamente.", AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al actualizar", "No se pudo actualizar el docente: " + e.getMessage(), AlertType.ERROR);
        }
    }
    
    private void actualizarTableView() {
        int selectedIndex = tbDocentes.getSelectionModel().getSelectedIndex();
        cargarDatosDeBaseDeDatos();
        tbDocentes.getSelectionModel().select(selectedIndex);
    }
    
    private void mostrarMensaje(String titulo, String cabecera, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    
    @FXML
    private void modificarDocente(ActionEvent event) {
        Docente docenteSeleccionado = tbDocentes.getSelectionModel().getSelectedItem();
        if (docenteSeleccionado != null) {
            actualizarDocente(docenteSeleccionado);
            guardarCambiosEnBaseDeDatos(docenteSeleccionado);
            actualizarTableView();
        }
    }
    
}
