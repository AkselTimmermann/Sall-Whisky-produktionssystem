package controller;

import storage.Storage;
import storage.StorageInterface;

public class Controller {
    private StorageInterface storage;

    Controller(StorageInterface storage){
    this.storage = storage;
    }


}
