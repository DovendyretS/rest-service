package main.game;

import java.util.ArrayList;

public interface GalgelogikI extends java.rmi.Remote{
    ArrayList<String> getBrugteBogstaver() throws java.rmi.RemoteException;

    String getSynligtOrd() throws java.rmi.RemoteException;

    int getAntalForkerteBogstaver() throws java.rmi.RemoteException;

}
