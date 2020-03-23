package com.spetrovici;
public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }
}