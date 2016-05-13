package com.development.shmulik.gevoicecontrol.models.modes;

import com.development.shmulik.gevoicecontrol.enums.VerticalDirection;
import com.development.shmulik.gevoicecontrol.exceptions.MethodNotSupportedException;
import com.development.shmulik.gevoicecontrol.models.Mode;

/**
 * Created by Shmulik on 5/13/2016.
 */
public class ColorMode extends Mode {
    @Override
    public void gain(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        System.out.println("Mode Color gain " + verticalDirection);
    }
}
