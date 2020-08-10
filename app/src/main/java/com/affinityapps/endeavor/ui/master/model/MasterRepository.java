package com.affinityapps.endeavor.ui.master.model;

import java.util.ArrayList;

public class MasterRepository {

    private static MasterRepository instance;
    private ArrayList<Master> dataset = new ArrayList<>();

    public static MasterRepository getInstance() {
        if(instance == null) {
            instance = new MasterRepository();
        }
        return instance;
    }


}
