package com.mongomythos.mongo_mythos.controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongomythos.mongo_mythos.domain.Mytho;
import org.bson.Document;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MythoDAO {
    //private MongoClient mongoClient;
    private MongoClient con;
    private MongoDatabase db;
    private static final String DATABASE_NAME = "cthulhu";



    public void conectarse()
    {
        con=ConnectionDB.conectar();
        db= con.getDatabase("cthulhu");

    }




    public void guardarMytho(Mytho mytho) {
//        Document documento = new Document()
//                .append("marca", coche.getMarca())
//                .append("modelo", coche.getModelo())
//                .append("matricula", coche.getMatricula())
//                .append("tipo", coche.getTipo());
//        db.getCollection(DATABASE_NAME).insertOne(documento);

        //MongoCollection<Coche> collection = db.getCollection("coches", Coche.class);
        //collection.insertOne(coche);
    }

    public void eliminarMytho(Mytho mytho) throws SQLException {

    }

    public void modificarMytho(Mytho mythoAntiguo, Mytho mythoNuevo) throws SQLException {

    }

    public List<Mytho> obtenerMythos() {
        this.conectarse();
        List<Mytho> mythos = new ArrayList<>();
        Document documento = new Document();


        FindIterable findIterable = db.getCollection("coche").find(documento);


        Iterator<Document> iter = findIterable.iterator();
        while (iter.hasNext()) {
            Document doc = iter.next();
            Mytho mytho = new Mytho();
            mytho.setId(doc.getObjectId("_id"));
            mytho.setNombre(doc.getString("nombre"));
            mytho.setGenero(doc.getString("modelo"));
            mytho.setTipo(doc.getString("marca"));
            mytho.setOrigen(doc.getString("matricula"));
            mytho.setTipo(doc.getString("tipo"));
            mythos.add(mytho);
        }

        return mythos;


    }

    public boolean existeMytho(String nombre) {
        return false;
    }
}
