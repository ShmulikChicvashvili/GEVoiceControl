package com.development.shmulik.gevoicecontrol.models.modes;

import com.development.shmulik.gevoicecontrol.enums.VerticalDirection;
import com.development.shmulik.gevoicecontrol.exceptions.MethodNotSupportedException;
import com.development.shmulik.gevoicecontrol.models.Mode;

/**
 * Created by Shmulik on 5/13/2016.
 */
public class TwoDimensionalMode extends Mode {
    @Override
    public void depth(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        System.out.println("Mode 2D depth " + verticalDirection);
    }

    @Override
    public void gain(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        System.out.println("Mode 2D gain " + verticalDirection);
    }
}
