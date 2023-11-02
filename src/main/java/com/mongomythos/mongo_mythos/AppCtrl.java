package com.mongomythos.mongo_mythos;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.net.URL;
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
    private TableColumn<?, ?> GeneroFullTbc;

    @FXML
    private TableColumn<?, ?> NombreFullTbc;

    @FXML
    private TableColumn<?, ?> NombreTbc;

    @FXML
    private TableColumn<?, ?> OrigenFullTbc;

    @FXML
    private TableColumn<?, ?> TipoFullTbc;

    @FXML
    private TableColumn<?, ?> TipoTbc;

    //Textfields
    @FXML
    private TextField NombreTxt;

    @FXML
    private TextField OrigenTxt;



    //Con esto cargamos todo desde el inicio
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Variable necesaria con Mongo para hacer la conexion
        MongoClient con;
        con = ConnectionDB.conectar();
        MongoDatabase database = con.getDatabase("cthulhu");
        //Me devuelve una coleccion si no existe la crea
        MongoCollection<Document> collection = database.getCollection("mythos");
        //System.out.println("Tamaño de la coleccion" + collection.count());
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
    Mytho mythoSelec;
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