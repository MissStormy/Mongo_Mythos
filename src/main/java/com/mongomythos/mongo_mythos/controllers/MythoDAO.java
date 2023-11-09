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
        Document documento = new Document()
                .append("nombre", mytho.getNombre())
                .append("genero", mytho.getGenero())
                .append("tipo", mytho.getTipo())
                .append("origen", mytho.getOrigen());
        db.getCollection(DATABASE_NAME).insertOne(documento);

        //MongoCollection<Coche> collection = db.getCollection("coches", Coche.class);
        //collection.insertOne(coche);
    }

    public void eliminarMytho(Mytho mytho) throws SQLException {
        MongoDatabase db = con.getDatabase("cthulhu");
        
    }

    public void modificarMytho(Mytho mythoAntiguo, Mytho mythoNuevo) throws SQLException {

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
