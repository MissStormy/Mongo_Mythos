package com.mongomythos.mongo_mythos.controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongomythos.mongo_mythos.domain.Mytho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.lang.reflect.Field;
import java.net.URL;
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
        MythoTbv.getItems().clear();
        List<Mytho> mythos = mythoDAO.obtenerMythos();

        MythoTbv.setItems(FXCollections.observableArrayList(mythos));

        //String[] generos = new String[]{"Masculino", "Femenino", "Otro"};
        GeneroCmb.setItems(FXCollections.observableArrayList(generos));
        GeneroCmb.getSelectionModel().selectFirst();

        //String[] tipos = new String[]{"Primigenio", "Dios exterior", "Monstruos"};
        TipoCmb.setItems(FXCollections.observableArrayList(tipos));
        TipoCmb.getSelectionModel().selectFirst();
    }

    private void poblarTablas(){

        List<Mytho> mythos = mythoDAO.obtenerMythos();
        for(Mytho e : mythos){
            System.out.println(e);
        }
        MythoTbv.setItems(FXCollections.observableArrayList(mythos).sorted());
        MythosFullTbv.setItems(FXCollections.observableArrayList(mythos).sorted());

        //Tabla pequeña
        this.NombreTbc.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.TipoTbc.setCellValueFactory(new PropertyValueFactory("tipo"));

        //Tabla extendida
        this.NombreFullTbc.setCellValueFactory(new PropertyValueFactory("nombre"));

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

    }

    void MythoSeleccionado(){
        mythoSelec = MythoTbv.getSelectionModel().getSelectedItem();
        System.out.println(mythoSelec);
    }
    //Este boton activara los textfield para introducir un nuevo mito
    @FXML
    void OnClickNew(ActionEvent event) {

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

    }


}