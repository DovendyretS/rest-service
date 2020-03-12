package main.game;

import java.util.ArrayList;

public interface GalgelogikI extends java.rmi.Remote{
    ArrayList<String> getBrugteBogstaver(String name) throws java.rmi.RemoteException;

    String getSynligtOrd(String name) throws java.rmi.RemoteException;

    int getAntalForkerteBogstaver(String name) throws java.rmi.RemoteException;

    boolean erSpilletSlut(String name) throws java.rmi.RemoteException;

    boolean erSpilletVundet(String name) throws java.rmi.RemoteException;

    boolean erSpilletTabt(String name) throws java.rmi.RemoteException;

    void gætBogstav(String bogstav, String name) throws java.rmi.RemoteException;

}
