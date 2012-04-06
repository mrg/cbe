package cbe.fetching.model;

import cbe.fetching.model.auto._CBEFetchingMap;

public class CBEFetchingMap extends _CBEFetchingMap {

    private static CBEFetchingMap instance;

    private CBEFetchingMap() {}

    public static CBEFetchingMap getInstance() {
        if(instance == null) {
            instance = new CBEFetchingMap();
        }

        return instance;
    }
}
