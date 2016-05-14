package com.development.shmulik.gevoicecontrol.models;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Shmulik on 5/14/2016.
 */
public class Commands {

    private static final String[] DOPLER_ON_COMMANDS = new String[]{"mode doppler on",
            "turn on mode doppler", "turn on doppler mode", "switch to mode doppler", "activate mode doppler"};
    private static final String[] DOPLER_OFF_COMMANDS = new String[]{"mode doppler off", "turn off mode doppler",
            "turn off doppler mode", "deactivate mode doppler"};
    private static final String[] M_ON_COMMANDS = new String[]{"mode m on",
            "turn on mode m", "turn on m mode", "switch to mode m", "activate mode m"};
    private static final String[] M_OFF_COMMANDS = new String[]{"mode m off", "turn off mode m",
            "turn off m mode", "deactivate mode m"};
    private static final String[] COLOR_ON_COMMANDS = new String[]{"mode color on",
            "turn on mode color", "turn on color mode", "switch to mode color", "activate mode color"};
    private static final String[] COLOR_OFF_COMMANDS = new String[]{"mode color off", "turn off mode color",
            "turn off color mode", "deactivate mode color"};
    private static final String[] PW_ON_COMMANDS = new String[]{"mode pw on",
            "turn on mode pw", "turn on pw mode", "switch to mode pw", "activate mode pw"};
    private static final String[] PW_OFF_COMMANDS = new String[]{"mode pw off", "turn off mode pw",
            "turn off pw mode", "deactivate mode pw"};
    private static final String[] DEPTH_COMMANDS = new String[]{"depth", "scale"};
    private static final String[] SCALE_COMMANDS = new String[]{"scale"};
    private static final String[] GAIN_COMMANDS = new String[]{"gain", "game"};
    private static final String[] SWEEP_COMMANDS = new String[]{"sweep", "sweet"};
    private static final String[] BASELINE_COMMANDS = new String[]{"baseline"};
    private static final String[] UP_COMMANDS = new String[]{"up"};
    private static final String[] DOWN_COMMANDS = new String[]{"down"};
    private static final String[] LEFT_COMMANDS = new String[]{"left"};
    private static final String[] RIGHT_COMMANDS = new String[]{"right", "write"};

    public static Hashtable<String, String> commandsMapping = new Hashtable<>();

    public static void init() {
        for (String s : M_ON_COMMANDS) {
            commandsMapping.put(s, "mode m on");
        }
        for (String s : M_OFF_COMMANDS) {
            commandsMapping.put(s, "mode m off");
        }
        for (String s : DOPLER_ON_COMMANDS) {
            commandsMapping.put(s, "mode doppler on");
        }
        for (String s : DOPLER_OFF_COMMANDS) {
            commandsMapping.put(s, "mode doppler off");
        }
        for (String s : PW_ON_COMMANDS) {
            commandsMapping.put(s, "mode pw on");
        }
        for (String s : PW_OFF_COMMANDS) {
            commandsMapping.put(s, "mode pw off");
        }
        for (String s : COLOR_ON_COMMANDS) {
            commandsMapping.put(s, "mode color on");
        }
        for (String s : COLOR_OFF_COMMANDS) {
            commandsMapping.put(s, "mode color off");
        }
        for (String s : DEPTH_COMMANDS) {
            commandsMapping.put(s, "depth");
        }
        for (String s : SCALE_COMMANDS) {
            commandsMapping.put(s, "scale");
        }
        for (String s : GAIN_COMMANDS) {
            commandsMapping.put(s, "gain");
        }
        for (String s : SWEEP_COMMANDS) {
            commandsMapping.put(s, "sweep");
        }
        for (String s : BASELINE_COMMANDS) {
            commandsMapping.put(s, "baseline");
        }
        for (String s : UP_COMMANDS) {
            commandsMapping.put(s, "up");
        }
        for (String s : DOWN_COMMANDS) {
            commandsMapping.put(s, "down");
        }
        for (String s : LEFT_COMMANDS) {
            commandsMapping.put(s, "left");
        }
        for (String s : RIGHT_COMMANDS) {
            commandsMapping.put(s, "right");
        }
    }
}
