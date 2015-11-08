package com.snow;

/**
 * Created by joshua on 06/11/2015.
 */
public class Rule {

    public final char NonTerminal;
    public final int NumConstructs;

    public Rule(char nonTerminal, int numConstructs) {
        NonTerminal = nonTerminal;
        NumConstructs = numConstructs;
    }
}
