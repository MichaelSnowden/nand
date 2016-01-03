package com.michaelsnowden.nand;

import java.io.IOException;
import java.util.Scanner;

import jline.console.ConsoleReader;

/**
 * @author michael.snowden
 */
public class REPL {
    public static void main(String[] args) throws IOException {
        ConsoleReader console = new ConsoleReader();
        console.setPrompt("nand> ");
        new NANDRuntime(new RuntimeDelegate() {
            @Override
            public void init() {
                System.out.println(Utils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("open.txt")));
            }

            @Override
            public void start(NANDRuntime runtime) {
                while (true) {
                    doNext(runtime);
                }
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
                try {
                    String line = console.readLine();
                    if (line == null) {
                        System.exit(0);
                    } else {
                        runtime.processLine(line);
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
