module com.mongomythos.mongo_mythos {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.mongomythos.mongo_mythos to javafx.fxml;
    exports com.mongomythos.mongo_mythos;
}