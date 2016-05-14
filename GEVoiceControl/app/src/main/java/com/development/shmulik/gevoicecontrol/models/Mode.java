package com.development.shmulik.gevoicecontrol.models;

import com.development.shmulik.gevoicecontrol.enums.HorizontalDirection;
import com.development.shmulik.gevoicecontrol.enums.VerticalDirection;
import com.development.shmulik.gevoicecontrol.exceptions.MethodNotSupportedException;

/**
 * Created by Shmulik on 5/13/2016.
 */
public abstract class Mode {

    private static final String TAG = "shmulik_develop";

    public void depth(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    public void gain(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    public void scale(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    public void sweep(HorizontalDirection horizontalDirection) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    public void baseline(VerticalDirection verticalDirection) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    public void drag(HorizontalDirection horizontalDirection) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    public void freeze() {
        // We implement here freeze logic cause all modes implement freeze in the same way
    }

    public void endExam() {
        // We implement here freeze logic cause all modes implement freeze in the same way
    }
}
