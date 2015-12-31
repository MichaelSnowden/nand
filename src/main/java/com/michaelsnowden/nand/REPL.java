package com.michaelsnowden.nand;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author michael.snowden
 */
public class REPL {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        new NANDRuntime(new RuntimeDelegate() {
            @Override
            public void init() {
                System.out.println(StreamUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("open.txt")));
            }

            @Override
            public void handleOutput(String output) {
                System.out.println(output);
            }

            @Override
            public void handleQuit() {
                System.out.println("Goodbye!");
            }

            @Override
            public void doNext(NANDRuntime runtime) {
                System.out.print("nand> ");
                try {
                    if (scanner.hasNextLine()) {
                        runtime.processLine(scanner.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void handleAssignment(String variable) {
                // Intentionally blank
            }
        });
    }
}
