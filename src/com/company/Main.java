package com.company;

import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Filer filer = new Filer("i1.txt",
                                "o1.txt");
        filer.readToBuffer();
        filer.countCharacters();
        // filer.writeToOutFile();
        filer.writeToOutfileWithoutCases();
    }
}
