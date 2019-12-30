package com.example.smartcity;

import android.app.Application;

import com.example.smartcity.model.InfoConnection;

public class MyApplication extends Application {
    private InfoConnection infoConnection;

    public InfoConnection getInfoConnection() {
        return infoConnection;
    }

    public void setInfoConnection(InfoConnection infoConnection) {
        this.infoConnection = infoConnection;
    }
}
