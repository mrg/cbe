package cbe.inserting.model;

import cbe.inserting.model.auto._CBEInsertingMap;

public class CBEInsertingMap extends _CBEInsertingMap {

    private static CBEInsertingMap instance;

    private CBEInsertingMap() {}

    public static CBEInsertingMap getInstance() {
        if(instance == null) {
            instance = new CBEInsertingMap();
        }

        return instance;
    }
}
