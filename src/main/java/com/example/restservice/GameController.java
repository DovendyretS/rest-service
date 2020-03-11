package com.example.restservice;

import org.springframework.http.HttpStatus;
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
        if ((mBrugerValidation.checkUserTimeout(name).getStatusCode()) == HttpStatus.OK){
            if (logik != null) {
                try {
                    return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(), logik.getBrugteBogstaver(),
                            logik.erSpilletSlut());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        return HttpStatus.FORBIDDEN;
    }

    @PostMapping("/game")
    @ResponseBody
    public Object guess(@RequestParam("guess") String guess, @RequestParam("name") String name) throws RemoteException, MalformedURLException, NotBoundException {
        getInformation();
        if ((mBrugerValidation.checkUserTimeout(name)).getStatusCode() == HttpStatus.OK) {
            logik.gætBogstav(guess);
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.FORBIDDEN;
        }
    }
}

