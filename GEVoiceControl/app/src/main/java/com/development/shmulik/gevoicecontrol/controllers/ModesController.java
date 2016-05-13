package com.development.shmulik.gevoicecontrol.controllers;

import com.development.shmulik.gevoicecontrol.enums.HorizontalDirection;
import com.development.shmulik.gevoicecontrol.enums.VerticalDirection;
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

    private void activateMode(Mode m) throws ModesCountExceededMaximumException, ModeAlreadyExistException {
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
                this.activateMode(m);
                break;
            case "off":
                this.deactivateMode(m);
                break;
            default:
                throw new CommandUnknownException();
        }
    }

    private void depthCommandActivation(String directionStr) throws CommandUnknownException {
        VerticalDirection verticalDirection;

        switch (directionStr) {
            case "up":
                verticalDirection = VerticalDirection.UP;
                break;
            case "down":
                verticalDirection = VerticalDirection.DOWN;
                break;
            default:
                throw new CommandUnknownException();
        }

        for(int i = 0; i < currentModesCount; i++) {
            try {
                currentModes.get(i).depth(verticalDirection);
            } catch (MethodNotSupportedException e) {
                // for now we do nothing
            }
        }
    }

    private void scaleCommandActivation(String directionStr) throws CommandUnknownException {
        VerticalDirection verticalDirection;

        switch (directionStr) {
            case "up":
                verticalDirection = VerticalDirection.UP;
                break;
            case "down":
                verticalDirection = VerticalDirection.UP;
                break;
            default:
                throw new CommandUnknownException();
        }

        for(int i = 0; i < currentModesCount; i++) {
            try {
                currentModes.get(i).scale(verticalDirection);
            } catch (MethodNotSupportedException e) {
                // for now we do nothing
            }
        }
    }

    private void gainCommandActivation(String directionStr) throws CommandUnknownException {
        VerticalDirection verticalDirection;

        switch (directionStr) {
            case "up":
                verticalDirection = VerticalDirection.UP;
                break;
            case "down":
                verticalDirection = VerticalDirection.DOWN;
                break;
            default:
                throw new CommandUnknownException();
        }

        for(int i = 0; i < currentModesCount; i++) {
            try {
                currentModes.get(i).gain(verticalDirection);
            } catch (MethodNotSupportedException e) {
                // for now we do nothing
            }
        }
    }

    private void sweepCommandActivation(String directionStr) throws CommandUnknownException {
        HorizontalDirection horizontalDirection;

        switch (directionStr) {
            case "left":
                horizontalDirection = HorizontalDirection.LEFT;
                break;
            case "right":
                horizontalDirection = HorizontalDirection.RIGHT;
                break;
            default:
                throw new CommandUnknownException();
        }

        for(int i = 0; i < currentModesCount; i++) {
            try {
                currentModes.get(i).sweep(horizontalDirection);
            } catch (MethodNotSupportedException e) {
                // for now we do nothing
            }
        }
    }

    private void baselineCommandActivation(String directionStr) throws CommandUnknownException {
        VerticalDirection verticalDirection;

        switch (directionStr) {
            case "up":
                verticalDirection = VerticalDirection.UP;
                break;
            case "down":
                verticalDirection = VerticalDirection.DOWN;
                break;
            default:
                throw new CommandUnknownException();
        }

        for(int i = 0; i < currentModesCount; i++) {
            try {
                currentModes.get(i).baseline(verticalDirection);
            } catch (MethodNotSupportedException e) {
                // for now we do nothing
            }
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
                if(commandParts.length != 2)
                    throw new CommandUnknownException();
                depthCommandActivation(commandParts[1]);
                break;
            case "scale":
                if(commandParts.length != 2)
                    throw new CommandUnknownException();
                scaleCommandActivation(commandParts[1]);
                break;
            case "gain":
                if(commandParts.length != 2)
                    throw new CommandUnknownException();
                gainCommandActivation(commandParts[1]);
                break;
            case "sweep":
                if(commandParts.length != 2)
                    throw new CommandUnknownException();
                sweepCommandActivation(commandParts[1]);
                break;
            case "baseline":
                if(commandParts.length != 2)
                    throw new CommandUnknownException();
                baselineCommandActivation(commandParts[1]);
            default:
                throw new CommandUnknownException();
        }

    }
}
