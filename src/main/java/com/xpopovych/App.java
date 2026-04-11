package com.xpopovych;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        FileRepository fileRepository = new FileRepository();
        WcService wcService = new WcService(fileRepository);
        WcDisplay wcDisplay = new WcDisplay();
        String filename;

        switch (args.length) {
            case 1 -> {
                filename = args[0];
                long[] result = wcService.execute(filename);
                wcDisplay.display(result, filename);
            }
            case 2 -> {
                filename = args[1];
                long result = wcService.execute(args[0], filename);
                wcDisplay.display(result, filename);
            }
            default -> {
                wcDisplay.displayHelpAttribute();
            }
        }
    }
}
