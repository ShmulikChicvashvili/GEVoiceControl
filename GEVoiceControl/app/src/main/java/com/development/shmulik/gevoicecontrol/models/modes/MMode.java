package com.development.shmulik.gevoicecontrol.models.modes;

import com.development.shmulik.gevoicecontrol.enums.HorizontalDirection;
import com.development.shmulik.gevoicecontrol.enums.VerticalDirection;
import com.development.shmulik.gevoicecontrol.exceptions.MethodNotSupportedException;
import com.development.shmulik.gevoicecontrol.models.Mode;

/**
 * Created by Shmulik on 5/13/2016.
 */
public class MMode extends Mode {
    @Override
    public void scale(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        System.out.println("Mode M scale " + verticalDirection);
    }

    @Override
    public void sweep(HorizontalDirection horizontalDirection) throws MethodNotSupportedException {
        System.out.println("Mode M sweep " + horizontalDirection);
    }
}
