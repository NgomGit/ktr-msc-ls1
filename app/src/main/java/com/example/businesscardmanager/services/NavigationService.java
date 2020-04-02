package com.example.businesscardmanager.services;

import android.app.Activity;
import android.content.Intent;

public class NavigationService {


    public static void goToActivity(Activity from , Class to){

        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }
}
