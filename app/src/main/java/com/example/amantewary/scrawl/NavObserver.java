package com.example.amantewary.scrawl;


import java.util.Observable;

public class NavObserver extends Observable{

    void callForAddLabel(String className){
        setChanged();
        notifyObservers(className);
    }

    void callForDrawerClose(String classname){
        setChanged();
        notifyObservers(classname);
    }

}
