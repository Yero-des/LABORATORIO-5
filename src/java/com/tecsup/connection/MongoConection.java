package com.tecsup.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoConection {

    String host = "localhost";
    int puerto = 27017;
    String nombreBD = "test";

    private MongoClient cliente;
    private MongoDatabase baseDatos;

    public MongoConection() {
        String uri = "mongodb://" + host + ":" + puerto + "/" + nombreBD;
        MongoClientURI clientURI = new MongoClientURI(uri);
        try {
            cliente = new MongoClient(clientURI);
            baseDatos = cliente.getDatabase(nombreBD);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public MongoDatabase getDatabase() {
        return baseDatos;
    }

    public void close() {
        cliente.close();
    }

}
