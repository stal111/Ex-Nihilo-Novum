package com.stal111.ex_nihilo.util;

import java.awt.*;

public class ColorUtils {

    public static Color average(Color colorLeft, Color colorRight, float leftWeight) {
        float rightWeight = 1.0F - leftWeight;
        float red = (float) Math.sqrt(colorLeft.getRed() * colorLeft.getRed() * leftWeight + colorRight.getRed() * colorRight.getRed() * rightWeight);
        float green = (float) Math.sqrt(colorLeft.getGreen() * colorLeft.getGreen() * leftWeight + colorRight.getGreen() * colorRight.getGreen() * rightWeight);
        float blue = (float) Math.sqrt(colorLeft.getBlue() * colorLeft.getBlue() * leftWeight + colorRight.getBlue() * colorRight.getBlue() * rightWeight);
        float alpha = (float) colorLeft.getAlpha() * leftWeight + colorRight.getAlpha() * rightWeight;
        return new Color(red, green, blue, alpha);
    }
}
