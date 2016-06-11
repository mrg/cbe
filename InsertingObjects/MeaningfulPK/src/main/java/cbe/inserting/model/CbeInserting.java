package cbe.inserting.model;

import cbe.inserting.model.auto._CbeInserting;

public class CbeInserting extends _CbeInserting {

    private static CbeInserting instance;

    private CbeInserting() {}

    public static CbeInserting getInstance() {
        if(instance == null) {
            instance = new CbeInserting();
        }

        return instance;
    }
}
