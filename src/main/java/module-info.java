module com.mongomythos.mongo_mythos {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires mongodb.driver;
    requires org.mongodb.bson;


    opens com.mongomythos.mongo_mythos to javafx.fxml;
    exports com.mongomythos.mongo_mythos;
}