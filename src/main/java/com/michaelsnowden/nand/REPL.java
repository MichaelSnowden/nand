package com.michaelsnowden.nand;

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import jline.console.ConsoleReader;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.MissingArgumentException;

/**
 * @author michael.snowden
 */
public class REPL {
    public static void main(String[] args) throws IOException {
        ConsoleReader console = new ConsoleReader();
        console.setPrompt("nand> ");

        Options opt = new Options();
        opt.addOption("i", true, "initialize nand with a file");

        CommandLine cmd = null;
        try {
            DefaultParser parser = new DefaultParser();
            cmd = parser.parse(opt, args);
        } catch (MissingArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final String initFile = (cmd != null && cmd.hasOption("i")) ? cmd.getOptionValue("i") : "";

        new NANDRuntime(new RuntimeDelegate() {
            @Override
            public void init() {
                System.out.println(Utils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("open.txt")));
            }

            @Override
            public void start(NANDRuntime runtime) {
                if (!initFile.equals("")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(initFile))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.length() > 0) {
                                runtime.processLine(line);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error: Could not load file " + initFile);
                    }
                }

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
