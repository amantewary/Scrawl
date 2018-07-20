package com.example.amantewary.scrawl;


import java.util.Observable;

public class NavObserver extends Observable{

    void callForAddLabel(String className){
        setChanged();
        notifyObservers(className);
    }

}
