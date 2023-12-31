package com.mongomythos.mongo_mythos.controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongomythos.mongo_mythos.domain.Mytho;
import com.mongomythos.mongo_mythos.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppCtrl implements Initializable {
    //Anchor Pane
    @FXML
    private AnchorPane ListPane;

    @FXML
    private AnchorPane SaveDataPane;

    //Botones
    @FXML
    private Button BorrarBtn;

    @FXML
    private Button CleanBtn;

    @FXML
    private Button ListBtn;

    @FXML
    private Button ModifyBtn;

    @FXML
    private Button NewBtn;

    @FXML
    private Button NewDataBtn;

    @FXML
    private Button SaveBtn;

    //Combobox
    @FXML
    private ComboBox<String> GeneroCmb;
    ObservableList<String> generos = FXCollections.observableArrayList("Masculino", "Femenino", "Otro");

    @FXML
    private ComboBox<String> TipoCmb;
    ObservableList<String> tipos = FXCollections.observableArrayList("Primigenio", "Dios Exterior", "Monstruo");

    //Tableviews
    @FXML
    private TableView<Mytho> MythoTbv;

    @FXML
    private TableView<Mytho> MythosFullTbv;

    //Tablecolumns
    @FXML
    private TableColumn<?, ?> NombreTbc;

    @FXML
    private TableColumn<?, ?> TipoTbc;

    @FXML
    private TableColumn<?, ?> GeneroFullTbc;

    @FXML
    private TableColumn<?, ?> NombreFullTbc;

    @FXML
    private TableColumn<?, ?> OrigenFullTbc;

    @FXML
    private TableColumn<?, ?> TipoFullTbc;


    //Textfields
    @FXML
    private TextField NombreTxt;

    @FXML
    private TextField OrigenTxt;

    private MythoDAO mythoDAO = new MythoDAO();

    private Mytho mythoSelec;
    MongoClient con;


    //Con esto cargamos todo desde el inicio
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mythoDAO.conectarse();
        cargarDatos();
        poblarTablas();
    }

    private void cargarDatos(){
        //String[] generos = new String[]{"Masculino", "Femenino", "Otro"};
        GeneroCmb.setItems(FXCollections.observableArrayList(generos));
        GeneroCmb.getSelectionModel().selectFirst();

        //String[] tipos = new String[]{"Primigenio", "Dios exterior", "Monstruos"};
        TipoCmb.setItems(FXCollections.observableArrayList(tipos));
        TipoCmb.getSelectionModel().selectFirst();
    }

    private void poblarTablas(){
        MythoTbv.getItems().clear();
        List<Mytho> mythos = mythoDAO.obtenerMythos();
        //System.out.println(mythos);
        for(Mytho e : mythos){
            System.out.println(e);
        }
        MythoTbv.setItems(FXCollections.observableArrayList(mythos));
        MythosFullTbv.setItems(FXCollections.observableArrayList(mythos));

        //Tabla pequeña
        NombreTbc.setCellValueFactory(new PropertyValueFactory("nombre"));
        TipoTbc.setCellValueFactory(new PropertyValueFactory("tipo"));

        //Tabla extendida
        NombreFullTbc.setCellValueFactory(new PropertyValueFactory("nombre"));
        GeneroFullTbc.setCellValueFactory(new PropertyValueFactory("genero"));
        TipoFullTbc.setCellValueFactory(new PropertyValueFactory("tipo"));
        OrigenFullTbc.setCellValueFactory(new PropertyValueFactory("origen"));

    }
    //Con esto limpiamos los textfield
    @FXML
    void OnClickClean(ActionEvent event) {
        NombreTxt.setText("");
        OrigenTxt.setText("");
    }
    //Cambiar la vista a la lista
    @FXML
    void OnClickList(ActionEvent event) {
        SaveDataPane.setVisible(false);
        ListPane.setVisible(true);
    }
    //Este activa los textfield y el tableview para elegir un mito a modificar
    @FXML
    void OnClickModify(ActionEvent event) {
        mythoSelec = MythoTbv.getSelectionModel().getSelectedItem();
        MythoSeleccionado(mythoSelec);

        editableView();
        ModifyBtn.setDisable(true);
        NewBtn.setDisable(false);

    }

    void MythoSeleccionado(Mytho mythoSelec){
        NombreTxt.setText(mythoSelec.getNombre());
        GeneroCmb.setValue(mythoSelec.getGenero());
        TipoCmb.setValue(mythoSelec.getTipo());
        OrigenTxt.setText(mythoSelec.getOrigen());
    }
    //Este boton activara los textfield para introducir un nuevo mito
    @FXML
    void OnClickNew(ActionEvent event) {
        editableView();
        ModifyBtn.setDisable(false);
        NewBtn.setDisable(true);
    }
    void editableView(){
        NombreTxt.setEditable(true);
        GeneroCmb.setEditable(true);
        TipoCmb.setEditable(true);
        OrigenTxt.setEditable(true);
    }
    //Boton lateral para cambiar de vista
    @FXML
    void OnClickNewData(ActionEvent event) {
        SaveDataPane.setVisible(true);
        ListPane.setVisible(false);
    }
    //Boton para guardar los datos
    @FXML
    void OnClickSave(ActionEvent event) {

        if (ModifyBtn.isDisable()){
            String nombre = NombreTxt.getText();
            boolean check = verify(nombre);
            if(check == false){
                AlertUtils.mostrarError("Eh!! Que te falta llenar esto");
            }else{

                String genero = GeneroCmb.getSelectionModel().getSelectedItem();
                String tipo = TipoCmb.getSelectionModel().getSelectedItem();
                String origen = OrigenTxt.getText();

                Mytho mytho = new Mytho(nombre, genero, tipo, origen);
                mythoDAO.modificarMytho(mythoSelec, mytho);

                reloadTable();
                //System.out.println("Modify esta desactivado!!");
            }

        }
        if(NewBtn.isDisable()){
            String nombre = NombreTxt.getText();
            boolean check = verify(nombre);
            if(check == false){
                AlertUtils.mostrarError("Eh!! Que te falta llenar esto");
            }else {

                String genero = GeneroCmb.getSelectionModel().getSelectedItem();
                String tipo = TipoCmb.getSelectionModel().getSelectedItem();
                String origen = OrigenTxt.getText();

                Mytho mytho = new Mytho(nombre, genero, tipo, origen);
                System.out.println(mytho.toString());
                mythoDAO.guardarMytho(mytho);

                reloadTable();
                //System.out.println("New esta desactivado!!");
            }
        }
    }
    @FXML
    void OnClickBorrar(ActionEvent event) throws SQLException {

        Mytho mytho = MythoTbv.getSelectionModel().getSelectedItem();
        mythoDAO.eliminarMytho(mytho);
        reloadTable();
    }

    void reloadTable(){
        poblarTablas();
    }
    boolean verify(String nombre){
        boolean correcto = false;
        if(nombre.equals("")){
            correcto = false;
        }else {
            correcto = true;
        }
        return correcto;
    }


}