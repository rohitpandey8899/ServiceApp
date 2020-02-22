package com.manish.obaa.utils;

import android.content.Context;

import android.view.animation.Interpolator;
import android.widget.Scroller;

public class SliderDurationScroller extends Scroller {

    private double scrollFactor = 1;

    public SliderDurationScroller(Context context) {
        super(context);
    }

    public SliderDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void setScrollDurationFactor(double scrollFactor) {
        this.scrollFactor = scrollFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int)(duration * scrollFactor));
    }
}