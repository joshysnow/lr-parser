package com.snow;


/**
 * Created by joshua on 06/11/2015.
 */
public class Action {

    public enum ActionType {
        SHIFT,
        REDUCE,
        ACCEPT
    }

    public final ActionType Type;
    public final int Value;

    public Action(ActionType type, int value) {
        Type = type;
        Value = value;
    }
}