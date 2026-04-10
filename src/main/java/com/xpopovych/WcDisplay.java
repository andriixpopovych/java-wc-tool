package com.xpopovych;

public class WcDisplay {
    public void display(long result, String filename) {
        System.out.println(result + " " + filename);
    }

    public void displayHelpAttribute() {
        System.out.println("jwc [-optional] filename");
    }
}
