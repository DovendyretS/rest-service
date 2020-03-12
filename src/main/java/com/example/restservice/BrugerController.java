package com.example.restservice;


import brugerautorisation.transport.rmi.Brugeradmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RestController
public class BrugerController {


    private Brugeradmin ba;
    private BrugerValidation mBrugerValidation = BrugerValidation.getInstance();

    @GetMapping("/login/{name}")
    public ResponseEntity checkLogin(@PathVariable ("name")String name) throws RemoteException, NotBoundException, MalformedURLException {
        ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        if (mBrugerValidation.checkBruger(name))
            return new ResponseEntity(HttpStatus.OK);

        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity validateUser(@RequestParam("name") String name,
                                       @RequestParam("password") String password) throws RemoteException, NotBoundException, MalformedURLException {

        ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        try {

            ba.hentBruger(name, password);
            mBrugerValidation.addBruger(name);

            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}

