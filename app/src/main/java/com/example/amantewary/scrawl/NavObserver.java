package com.example.amantewary.scrawl;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import java.util.Observable;

public class NavObserver extends Observable{

    void callForAddLabel(String className){
        setChanged();
        notifyObservers(className);
    }

    void callForDrawerClose(String className){
        setChanged();
        notifyObservers(className);

    }

}
