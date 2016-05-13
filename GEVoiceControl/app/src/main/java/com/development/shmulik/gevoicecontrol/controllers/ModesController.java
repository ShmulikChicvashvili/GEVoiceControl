package com.development.shmulik.gevoicecontrol.controllers;

import com.development.shmulik.gevoicecontrol.exceptions.*;
import com.development.shmulik.gevoicecontrol.exceptions.IllegalStateException;
import com.development.shmulik.gevoicecontrol.models.Mode;
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

    // this method will get nasty real quick :(
    public void parseCommand(String command) {
    }
}
