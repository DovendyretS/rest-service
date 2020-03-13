package com.example.restservice;

import brugerautorisation.data.Bruger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BrugerValidation {

    private HashMap<String, Long> brugere = new HashMap<>();
    private long lastActive;
    private static BrugerValidation instance;

    private BrugerValidation(){}

    // Singleton class

    public static BrugerValidation getInstance(){
        if (BrugerValidation.instance == null){
            BrugerValidation.instance = new BrugerValidation();
        }
        return BrugerValidation.instance;
    }

    // Saves all users in a hashmap with their name and last time they were active
    public void addBruger(String name){
        brugere.put(name,System.currentTimeMillis());
    }

    // checks if a user is present in our map
    public boolean checkBruger(String name){
        if (brugere.get(name) != null) {
            if (checkUserTimeout(name).getStatusCode() == HttpStatus.FORBIDDEN) {
                return false;
            } else
                return true;
        }else
            return false;
    }


    // Checks if the user has been "afk" for more than 5 minutes and will return a
    // FORBIDDEN status code meaning the user will have to login again on the client-side
    // otherwise it returns OK
    public ResponseEntity checkUserTimeout(String name) {

        lastActive = System.currentTimeMillis();

        long timeGap = 300000; // equal to 5 minutes

        Iterator it = brugere.entrySet().iterator();

        while (it.hasNext()) { // loop through the hashmap with users

            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().equals(name)) {

                if (lastActive > brugere.get(name) + timeGap) {

                    return new ResponseEntity(HttpStatus.FORBIDDEN); // will return code 403 if the user has been inactive for more than 5 minutes
                } else {

                    lastActive = System.currentTimeMillis();
                    brugere.replace(name, lastActive); // updates lastActive for a user in the map
                }
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
