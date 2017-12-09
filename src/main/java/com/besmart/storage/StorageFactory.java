package com.besmart.storage;

import com.besmart.utils.Configurations;
import com.besmart.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StorageFactory {
    @Autowired
    Configurations configurations;

    @Autowired
    StorageInMemory storageInMemory;

    @Autowired
    StorageFile storageFile;

    public StorageBase getStorage() {
        if (configurations.getStorageMode().equalsIgnoreCase(Constants.IN_MEMORY)) {
            return storageInMemory;
        } else if (configurations.getStorageMode().equalsIgnoreCase(Constants.IN_FILE)) {
            return storageFile;
        }

        return null;
    }
}
