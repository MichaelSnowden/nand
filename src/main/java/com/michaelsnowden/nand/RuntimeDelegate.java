package com.michaelsnowden.nand;

/**
 * @author michael.snowden
 */
public interface RuntimeDelegate {
    void init(NANDRuntime runtime);
    void handleOutput(String output);
    void handleQuit();
    void doNext(NANDRuntime runtime);
    void handleAssignment(String variable);
}
