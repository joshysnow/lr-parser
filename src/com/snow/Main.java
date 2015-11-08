package com.snow;

import com.snow.Action.ActionType;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        final int STATE_0 = 0x00;
        final int STATE_1 = 0x01;
        final int STATE_2 = 0x02;
        final int STATE_3 = 0x04;
        final int STATE_4 = 0x08;
        final int STATE_5 = 0x10;
        final int STATE_6 = 0x20;
        final int STATE_7 = 0x40;
        final int STATE_8 = 0x80;

        final int RULE_1 = 0x00;
        final int RULE_2 = 0x01;
        final int RULE_3 = 0x02;
        final int RULE_4 = 0x04;

        final char TERMINAL_w = 'w';
        final char TERMINAL_colon = ':';
        final char TERMINAL_comma = ',';
        final char TERMINAL_dollar = '$';

        final char NON_TERMINAL_E = 'E';
        final char NON_TERMINAL_S = 'S';
        final char NON_TERMINAL_W = 'W';

        // Create all rules.
        Rule rule_1 = new Rule(NON_TERMINAL_E, 1);     // E -> S
        Rule rule_2 = new Rule(NON_TERMINAL_E, 3);     // E -> E , S
        Rule rule_3 = new Rule(NON_TERMINAL_S, 3);     // S -> W : W
        Rule rule_4 = new Rule(NON_TERMINAL_W, 1);     // W -> w

        // Create rule table.
        HashMap<Integer, Rule> rules = new HashMap<>(4);
        rules.put(RULE_1, rule_1);
        rules.put(RULE_2, rule_2);
        rules.put(RULE_3, rule_3);
        rules.put(RULE_4, rule_4);

        // Create all actions.
        Action action_s0_w      = new Action(ActionType.SHIFT,  STATE_8);
        Action action_s1_comma  = new Action(ActionType.SHIFT,  STATE_3);
        Action action_s1_dollar = new Action(ActionType.ACCEPT, STATE_1);
        Action action_s2_colon  = new Action(ActionType.REDUCE, RULE_1);
        Action action_s2_comma  = new Action(ActionType.REDUCE, RULE_1);
        Action action_s2_w      = new Action(ActionType.REDUCE, RULE_1);
        Action action_s2_dollar = new Action(ActionType.REDUCE, RULE_1);
        Action action_s3_w      = new Action(ActionType.SHIFT,  STATE_8);
        Action action_s4_colon  = new Action(ActionType.SHIFT,  STATE_5);
        Action action_s5_w      = new Action(ActionType.SHIFT,  STATE_8);
        Action action_s6_colon  = new Action(ActionType.REDUCE, RULE_3);
        Action action_s6_comma  = new Action(ActionType.REDUCE, RULE_3);
        Action action_s6_w      = new Action(ActionType.REDUCE, RULE_3);
        Action action_s6_dollar = new Action(ActionType.REDUCE, RULE_3);
        Action action_s7_colon  = new Action(ActionType.REDUCE, RULE_2);
        Action action_s7_comma  = new Action(ActionType.REDUCE, RULE_2);
        Action action_s7_w      = new Action(ActionType.REDUCE, RULE_2);
        Action action_s7_dollar = new Action(ActionType.REDUCE, RULE_2);
        Action action_s8_colon  = new Action(ActionType.REDUCE, RULE_4);
        Action action_s8_comma  = new Action(ActionType.REDUCE, RULE_4);
        Action action_s8_w      = new Action(ActionType.REDUCE, RULE_4);
        Action action_s8_dollar = new Action(ActionType.REDUCE, RULE_4);

        // Create state actions tables.
        HashMap<Character, Action> s0_actions = new HashMap<>(1);
        s0_actions.put(TERMINAL_w, action_s0_w);

        HashMap<Character, Action> s1_actions = new HashMap<>(2);
        s1_actions.put(TERMINAL_comma,  action_s1_comma);
        s1_actions.put(TERMINAL_dollar, action_s1_dollar);

        HashMap<Character, Action> s2_actions = new HashMap<>(4);
        s2_actions.put(TERMINAL_colon,  action_s2_colon);
        s2_actions.put(TERMINAL_comma,  action_s2_comma);
        s2_actions.put(TERMINAL_w,      action_s2_w);
        s2_actions.put(TERMINAL_dollar, action_s2_dollar);

        HashMap<Character, Action> s3_actions = new HashMap<>(1);
        s3_actions.put(TERMINAL_w, action_s3_w);

        HashMap<Character, Action> s4_actions = new HashMap<>(1);
        s4_actions.put(TERMINAL_colon, action_s4_colon);

        HashMap<Character, Action> s5_actions = new HashMap<>(1);
        s5_actions.put(TERMINAL_w, action_s5_w);

        HashMap<Character, Action> s6_actions = new HashMap<>(4);
        s6_actions.put(TERMINAL_colon,  action_s6_colon);
        s6_actions.put(TERMINAL_comma,  action_s6_comma);
        s6_actions.put(TERMINAL_w,      action_s6_w);
        s6_actions.put(TERMINAL_dollar, action_s6_dollar);

        HashMap<Character, Action> s7_actions = new HashMap<>(4);
        s7_actions.put(TERMINAL_colon,  action_s7_colon);
        s7_actions.put(TERMINAL_comma,  action_s7_comma);
        s7_actions.put(TERMINAL_w,      action_s7_w);
        s7_actions.put(TERMINAL_dollar, action_s7_dollar);

        HashMap<Character, Action> s8_actions = new HashMap<>(4);
        s8_actions.put(TERMINAL_colon,  action_s8_colon);
        s8_actions.put(TERMINAL_comma,  action_s8_comma);
        s8_actions.put(TERMINAL_w,      action_s8_w);
        s8_actions.put(TERMINAL_dollar, action_s8_dollar);

        // Create action table.
        HashMap<Integer, HashMap<Character, Action>> actions = new HashMap<>(9);
        actions.put(STATE_0, s0_actions);
        actions.put(STATE_1, s1_actions);
        actions.put(STATE_2, s2_actions);
        actions.put(STATE_3, s3_actions);
        actions.put(STATE_4, s4_actions);
        actions.put(STATE_5, s5_actions);
        actions.put(STATE_6, s6_actions);
        actions.put(STATE_7, s7_actions);
        actions.put(STATE_8, s8_actions);

        // Create state go-to's.
        HashMap<Character, Integer> s0_goto = new HashMap<>(3);
        s0_goto.put(NON_TERMINAL_E, STATE_1);
        s0_goto.put(NON_TERMINAL_S, STATE_2);
        s0_goto.put(NON_TERMINAL_W, STATE_4);

        HashMap<Character, Integer> s3_goto = new HashMap<>(2);
        s3_goto.put(NON_TERMINAL_S, STATE_7);
        s3_goto.put(NON_TERMINAL_W, STATE_4);

        HashMap<Character, Integer> s5_goto = new HashMap<>(1);
        s5_goto.put(NON_TERMINAL_W, STATE_6);

        // Create go-to table.
        HashMap<Integer, HashMap<Character, Integer>> gotos = new HashMap<>(3);
        gotos.put(STATE_0, s0_goto);
        gotos.put(STATE_3, s3_goto);
        gotos.put(STATE_5, s5_goto);

        LRParser parser = new LRParser(rules, actions, gotos);
        boolean result = parser.Parse("w:w,w:w$");

        System.out.println("String parsed: " + (result ? "YES" : "NO"));
    }
}
