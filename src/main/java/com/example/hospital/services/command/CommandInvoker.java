package com.example.hospital.services.command;

public class CommandInvoker {
    
    public void executeCommand(Icommand command) {
        command.execute();
    }
}
