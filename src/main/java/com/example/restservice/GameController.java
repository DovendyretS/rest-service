package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


@RestController
public class GameController  {


    private main.game.GalgelogikI logik;
    private BrugerValidation mBrugerValidation = BrugerValidation.getInstance();

    public void getInformation() throws RemoteException, NotBoundException, MalformedURLException {
        logik = (main.game.GalgelogikI) Naming.lookup("rmi://dist.saluton.dk:20123/logics185123");
   }

    @GetMapping("/game/{name}")
    @ResponseBody
    public Object getGame(@PathVariable String name) throws RemoteException, NotBoundException, MalformedURLException {
       getInformation();
        if ((mBrugerValidation.checkUserTimeout(name).getStatusCode()) == HttpStatus.OK){ // checks if a user has been timed out
            if (logik != null) {
                try {
                    if (logik.erSpilletSlut(name)) // if the game has ended we will also send the word the user has guessed
                        return new Game(logik.getSynligtOrd(name), logik.getAntalForkerteBogstaver(name), logik.getBrugteBogstaver(name),
                                String.valueOf(logik.erSpilletSlut(name)), logik.getOrdet(name));
                    else                           // returns a game as a json with necessary information
                        return new Game(logik.getSynligtOrd(name), logik.getAntalForkerteBogstaver(name), logik.getBrugteBogstaver(name), String.valueOf(logik.erSpilletSlut(name)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/game")
    @ResponseBody
    public ResponseEntity guess(@RequestParam("name") String guess, @RequestParam("guess") String name) throws RemoteException, MalformedURLException, NotBoundException {
        getInformation();
        if ((mBrugerValidation.checkUserTimeout(name)).getStatusCode() == HttpStatus.OK) { // checks if a user has been timed out
            if (logik != null) {
                logik.gætBogstav(guess, name);
                return new ResponseEntity(HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/game")
    public ResponseEntity endGame(@RequestParam("name") String name) throws RemoteException {
        if ((mBrugerValidation.checkUserTimeout(name)).getStatusCode() == HttpStatus.OK) {
            logik.nulstil(name); // resets the game
            return new ResponseEntity(HttpStatus.OK);
        }else
            return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}

