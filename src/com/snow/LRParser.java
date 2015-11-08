package com.snow;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by joshua on 06/11/2015.
 */
public class LRParser {

    private final HashMap<Integer, Rule> _rules;
    private final HashMap<Integer, HashMap<Character, Action>> _actions;
    private final HashMap<Integer, HashMap<Character, Integer>> _gotos;

    public LRParser(HashMap<Integer, Rule> rules, HashMap<Integer, HashMap<Character, Action>> actions,
                    HashMap<Integer, HashMap<Character, Integer>> gotos)
    {
        _rules = rules;
        _actions = actions;
        _gotos = gotos;
    }

    public boolean Parse(String inputStream)
    {
        char[] characters = inputStream.toCharArray();

        // Create state stack and set the initial state to 0.
        Stack<Integer> stateStack = new Stack<>();
        stateStack.push(0);

        int index = 0;
        int length = characters.length;
        boolean finished = false;
        boolean parsed = false;

        // Parse string using the LR parsing algorithm.
        while(index < length && !finished)
        {
            // Get the next character from the input stream.
            char inputChar = characters[index];
            int currentState = stateStack.peek();

            // Get action.
            HashMap<Character, Action> stateActions = _actions.get(currentState);
            Action action;

            // Check the state has a set of actions.
            if (stateActions != null)
            {
                action = stateActions.get(inputChar);
            }
            else
            {
                // Parse error.
                System.err.println("Parse error, parsing table incomplete, missing state in actions" +
                        " table or invalid state passed onto stack from a shift action.");
                break;
            }

            // If an action exists for this input in the parsing table.
            if (action != null)
            {
                switch (action.Type)
                {
                    case SHIFT:
                        {
                            // Push the next state onto the stack.
                            stateStack.push(action.Value);

                            // Shift onto next input character.
                            index++;
                        }
                        break;
                    case REDUCE:
                        {
                            while(true)
                            {
                                // Get the rule no. to reduce with.
                                int ruleNo = action.Value;

                                // Get the rule object from the rules table.
                                Rule rule = _rules.get(ruleNo);

                                // If such a rule exists.
                                if (rule == null)
                                {
                                    System.err.println("Parse error, reduce rule doesn't exist. Either the action table has an" +
                                            " incorrect rule number or the rule table is missing the desired rule.");
                                    break;
                                }

                                // Pop x number of states off the stack equal to the
                                // number of constructs to make the non-terminal X.
                                if (rule.NumConstructs >= stateStack.size())
                                {
                                    System.err.println("Parse error, invalid number of states to pop off the stack," +
                                            " suggests incorrect parse table.");
                                    break;
                                }

                                // Pop states off the stack.
                                for (int i = 0; i < rule.NumConstructs; i++)
                                {
                                    stateStack.pop();
                                }

                                // Get the next state from the goto table.
                                currentState = stateStack.peek();

                                HashMap<Character, Integer> stateGotos = _gotos.get(currentState);

                                if (stateGotos == null)
                                {
                                    System.err.println("Parse error, state has no go to table, invalid parse.");
                                    break;
                                }

                                // Push the next 'goto' state onto the stack.
                                if (stateGotos.containsKey(rule.NonTerminal))
                                {
                                    int nextState = stateGotos.get(rule.NonTerminal);
                                    stateStack.push(nextState);
                                }
                                else
                                {
                                    System.err.println("Parse error, state has no go to for the non-terminal provided.");
                                    break;
                                }

                                break;
                            }

                        }
                        break;
                    case ACCEPT:
                        {
                            // Parse complete, check if we consumed input stream.
                            if (index == length - 1)
                            {
                                parsed = true;
                            }

                            // Break out of the loop.
                            finished = true;
                        }
                        break;
                }
            }
            else
            {
                System.err.println("Parse error, no action for the terminal in current state.");
                break;
            }
        }

        return parsed;
    }
}
