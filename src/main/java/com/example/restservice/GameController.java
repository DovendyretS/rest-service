package com.example.restservice;

import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


@RestController
public class GameController  {

    private main.game.GalgelogikI logik;


    public void getInformation() throws RemoteException, NotBoundException, MalformedURLException {
       logik = (main.game.GalgelogikI) Naming.lookup("rmi://dist.saluton.dk:20123/logics185123");
   }

    @GetMapping("/game")
    public Game getGame() throws RemoteException, NotBoundException, MalformedURLException {
       getInformation();

        if (logik != null) {
            try {
                return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(), logik.getBrugteBogstaver(),
                        logik.erSpilletSlut());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @PostMapping("/game")
    public void guess(@RequestParam("guess") String guess) throws RemoteException, MalformedURLException, NotBoundException {
        getInformation();
        logik.gætBogstav(guess);
    }
}

