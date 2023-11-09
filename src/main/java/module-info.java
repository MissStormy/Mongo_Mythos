module com.mongomythos.mongo_mythos {
    requires javafx.controls;
    requires javafx.fxml;
            
    requires org.controlsfx.controls;
    requires mongodb.driver;
    requires org.mongodb.bson;
    requires java.sql;


    opens com.mongomythos.mongo_mythos to javafx.fxml;
    exports com.mongomythos.mongo_mythos;
    exports com.mongomythos.mongo_mythos.controllers;
    exports com.mongomythos.mongo_mythos.domain;
    opens com.mongomythos.mongo_mythos.controllers to javafx.fxml;
    opens com.mongomythos.mongo_mythos.domain to javafx.fxml;
}