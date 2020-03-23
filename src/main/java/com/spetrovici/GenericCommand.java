package com.spetrovici;

import java.io.IOException;
import java.util.Scanner;

public interface GenericCommand {
    public void run() throws InvalidCatalogException;
    public String getName();
}
