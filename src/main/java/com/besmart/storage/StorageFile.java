package com.besmart.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StorageFile implements StorageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageFile.class);

    // When we will use also StorageFile we need to implements all the method below (meanwhile i use "default" in interface signature)
}
