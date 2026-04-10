package com.xpopovych;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        FileRepository fileRepository = new FileRepository();
        WcService wcService = new WcService(fileRepository);
        WcDisplay wcDisplay = new WcDisplay();
        long result;
        String filename;

        switch (args.length) {
            case 1 -> {
                filename = args[0];
                result = wcService.execute(filename);
            }
            case 2 -> {
                filename = args[1];
                result = wcService.execute(args[0], filename);
            }
            default -> {
                wcDisplay.displayHelpAttribute();
                return;
            }
        }
        wcDisplay.display(result, filename);
    }
}
