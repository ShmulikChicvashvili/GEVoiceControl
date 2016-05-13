package com.development.shmulik.gevoicecontrol.controllers;

import com.development.shmulik.gevoicecontrol.exceptions.*;
import com.development.shmulik.gevoicecontrol.exceptions.IllegalStateException;
import com.development.shmulik.gevoicecontrol.models.Mode;
import com.development.shmulik.gevoicecontrol.models.modes.ColorMode;
import com.development.shmulik.gevoicecontrol.models.modes.DoplerMode;
import com.development.shmulik.gevoicecontrol.models.modes.MMode;
import com.development.shmulik.gevoicecontrol.models.modes.PWMode;
import com.development.shmulik.gevoicecontrol.models.modes.TwoDimensionalMode;

import java.util.ArrayList;

/**
 * Created by Shmulik on 5/13/2016.
 */
public class ModesController {

    private final int MINIMUM_MODES = 1;
    private final int MAXIMUM_MODES = 3;

    private ArrayList<Mode> currentModes;
    private int currentModesCount;

    public ModesController() {
        // As I saw in the video the maximum amount of modes supported in the same time are 3
        currentModes = new ArrayList<>(MAXIMUM_MODES);
        // 2DMode is always on
        currentModes.add(0, new TwoDimensionalMode());
        currentModesCount = 1;
    }

    private void activatedMode(Mode m) throws ModesCountExceededMaximumException, ModeAlreadyExistException {
        if (currentModesCount == MAXIMUM_MODES)
            throw new ModesCountExceededMaximumException();

        for (int i = 0; i < MAXIMUM_MODES; i++) {
            if (currentModes.get(i).getClass() == m.getClass())
                throw new ModeAlreadyExistException();
        }
        currentModes.add(m);
        currentModesCount++;
    }

    private void deactivateMode(Mode m) throws IllegalStateException, ModeDoesntExistException {
        if (currentModesCount == MINIMUM_MODES || m instanceof TwoDimensionalMode)
            throw new IllegalStateException();

        int foundIndex = -1;
        for (int i = MINIMUM_MODES; i < MAXIMUM_MODES; i++) {
            if (currentModes.get(i).getClass() == m.getClass())
                foundIndex = i;
        }
        if (foundIndex == -1)
            throw new ModeDoesntExistException();

        currentModes.remove(foundIndex);
    }

    private void modeCommandActivation(String modeStr, String switchStr) throws CommandUnknownException, ModesCountExceededMaximumException, ModeAlreadyExistException, IllegalStateException, ModeDoesntExistException {
        Mode m;

        switch (modeStr) {
            case "color":
                m = new ColorMode();
                break;
            case "dopler":
                m = new DoplerMode();
                break;
            case "m":
                m = new MMode();
                break;
            case "pw":
                m = new PWMode();
                break;
            default:
                throw new CommandUnknownException();
        }

        switch (switchStr) {
            case "on":
                this.activatedMode(m);
                break;
            case "off":
                this.deactivateMode(m);
                break;
            default:
                throw new CommandUnknownException();
        }
    }

    // this method will get nasty real quick :(
    public void parseCommand(String command) throws CommandUnknownException, ModeAlreadyExistException, ModesCountExceededMaximumException, ModeDoesntExistException, IllegalStateException {
        String[] commandParts = command.split(" ");
        String commandNoun = commandParts[0];

        switch (commandNoun) {
            case "mode":
                if (commandParts.length != 3)
                    throw new CommandUnknownException();
                modeCommandActivation(commandParts[1], commandParts[2]);
                break;
            case "depth":
                break;
            case "scale":
                break;
            case "gain":
                break;
            case "sweep":
                break;
            case "baseline":
            default:
                throw new CommandUnknownException();
        }

    }
}
