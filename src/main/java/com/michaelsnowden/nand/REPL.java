package com.michaelsnowden.nand;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import jline.console.ConsoleReader;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

/**
 * @author michael.snowden
 */
public class REPL {
    public static void main(String[] args) throws IOException {
        ConsoleReader console = new ConsoleReader();
        console.setPrompt("nand> ");

        Options opt = new Options();
        opt.addOption("i", true, "initialize nand with a file");

        try {
            DefaultParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(opt, args);

            new NANDRuntime(new RuntimeDelegate() {
                @Override
                public void init(NANDRuntime runtime) {
                    if(cmd.hasOption("i")) {
                        try (BufferedReader br = new BufferedReader(new FileReader(cmd.getOptionValue("i")))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                if (line.length() > 0) {
                                    runtime.processLine(line);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error: Could not load file " + cmd.getOptionValue("i"));
                        }
                    }
                    System.out.println(Utils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("open.txt")));
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
                        String line = null;
                        if ((line = console.readLine()) != null) {
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
