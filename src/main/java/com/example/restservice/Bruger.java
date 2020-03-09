package com.example.restservice;

import java.io.*;

public class Bruger implements Serializable
{

    private String name;
    private String password;

    public Bruger(String name, String password) {
        this.name = name;
        this.password = password;
    }

}