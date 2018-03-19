package com.rk.karaf;

import java.util.List;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

public class CommandCompleter implements Completer
{

    public int complete(String buffer, int cursor, List<String> candidates)
    {
        StringsCompleter delegate=new StringsCompleter();
        delegate.getStrings().add("option1");
        delegate.getStrings().add("option2");
        delegate.getStrings().add("etc");
        
        return delegate.complete(buffer, cursor, candidates);
    }

}
