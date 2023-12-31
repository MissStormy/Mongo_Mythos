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

import static com.mongodb.client.model.Filters.eq;

public class MythoDAO {
    //private MongoClient mongoClient;
    private MongoClient con;
    private MongoDatabase db;
    private static final String DATABASE_NAME = "cthulhu";
    private static final String COLLECTION_NAME = "mythos";



    public void conectarse()
    {
        con=ConnectionDB.conectar();
        db= con.getDatabase("cthulhu");

    }
    public void guardarMytho(Mytho mytho) {
        Document documento = new Document()
                .append("nombre", mytho.getNombre())
                .append("genero", mytho.getGenero())
                .append("tipo", mytho.getTipo())
                .append("origen", mytho.getOrigen());
        db.getCollection(COLLECTION_NAME).insertOne(documento);


    }

    public void eliminarMytho(Mytho mytho) {
       db.getCollection(COLLECTION_NAME).deleteOne(new Document("nombre", mytho.getNombre()));

    }

    public void modificarMytho(Mytho mythoAntiguo, Mytho mythoNuevo) {
        db.getCollection(COLLECTION_NAME).replaceOne(new Document("_id", mythoAntiguo.getId()),
                new Document()
                .append("nombre", mythoNuevo.getNombre())
                .append("genero", mythoNuevo.getGenero())
                .append("tipo", mythoNuevo.getTipo())
                .append("origen", mythoNuevo.getOrigen()));
    }

    public List<Mytho> obtenerMythos() {
        //this.conectarse();
        List<Mytho> mythos = new ArrayList<>();
        Document documento = new Document();


        FindIterable findIterable = db.getCollection("mythos").find(documento);


        Iterator<Document> iter = findIterable.iterator();
        while (iter.hasNext()) {
            //System.out.println("hola");
            Document doc = iter.next();
            Mytho mytho = new Mytho();
            mytho.setId(doc.getObjectId("_id"));
            mytho.setNombre(doc.getString("nombre"));
            mytho.setGenero(doc.getString("genero"));
            mytho.setTipo(doc.getString("tipo"));
            mytho.setOrigen(doc.getString("origen"));
            mythos.add(mytho);
        }

        return mythos;


    }

    public boolean existeMytho(String nombre) {
        return false;
    }
}
