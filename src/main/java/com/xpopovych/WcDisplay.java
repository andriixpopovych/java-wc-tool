package com.xpopovych;

public class WcDisplay {
    public void display(long result, String filename) {
        System.out.println(result + " " + filename);
    }

    public void display(long[] result, String filename) {
        for (long l : result) {
            System.out.print(l + " ");
        }
        System.out.print(filename);
    }

    public void displayHelpAttribute() {
        System.out.println("jwc [-optional] filename");
    }
}
