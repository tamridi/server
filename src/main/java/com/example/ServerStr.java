package com.example;
import java.util.*;
import java.net.*;
import java.io.*;

public class ServerStr
{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi() {
        try {
            System.out.println("SERVER partito in esecuzione ...");
            server = new ServerSocket(3000);
            client = server.accept();
            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica() {
        try {
            stringaRicevuta = inDalClient.readLine();
            System.out.println("Ricevuta la stringa dal client : " + stringaRicevuta);
            stringaModificata = modificaStringa(stringaRicevuta);
            System.out.println("Invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata + '\n');
            System.out.println("Stringa modificata inviata al client, chiusura connessione");
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("something went wrong!");
            System.exit(1);
        }
    }

    public String modificaStringa(String stringaRicevuta) {
        return stringaRicevuta.toUpperCase();
    }
}