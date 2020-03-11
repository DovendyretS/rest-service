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

    // Checks if the user has been "afk" for more than 5 minutes and will return a
    // FORBIDDEN status code meaning the user will have to login again on the client-side
    // otherwise it returns OK
    public ResponseEntity checkUserTimeout(String name) {
        System.out.println(brugere.toString());
        lastActive = System.currentTimeMillis();
        long timeGap = 300000;

        Iterator it = brugere.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().equals(name)) {

                if (lastActive > brugere.get(name) + timeGap) {

                    return new ResponseEntity(HttpStatus.FORBIDDEN);
                } else {

                    lastActive = System.currentTimeMillis();
                    brugere.replace(name, lastActive);
                }
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
