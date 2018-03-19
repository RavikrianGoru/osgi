package com.rk.karaf;

import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;


/**
 * Displays the last log entries
 */
@Command(scope = "custom", name = "rkcmd", description = "this is custom command for testing")
public class Rkcmd extends OsgiCommandSupport {

    protected Object doExecute() throws Exception {
         System.out.println("Executing command rkcmd");
         return null;
    }
}
