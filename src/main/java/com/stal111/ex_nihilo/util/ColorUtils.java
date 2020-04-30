package com.stal111.ex_nihilo.util;

import java.awt.*;

public class ColorUtils {

    public static Color average(Color colorLeft, Color colorRight, float leftWeight) {
        float rightWeight = 1.0F - leftWeight;
        int red = (int) Math.sqrt(colorLeft.getRed() * colorLeft.getRed() * leftWeight + colorRight.getRed() * colorRight.getRed() * rightWeight);
        System.out.println(red);
        int green = (int) Math.sqrt(colorLeft.getGreen() * colorLeft.getGreen() * leftWeight + colorRight.getGreen() * colorRight.getGreen() * rightWeight);
        System.out.println(green);

        int blue = (int) Math.sqrt(colorLeft.getBlue() * colorLeft.getBlue() * leftWeight + colorRight.getBlue() * colorRight.getBlue() * rightWeight);
        System.out.println(blue);

        int alpha = (int) (colorLeft.getAlpha() * leftWeight + colorRight.getAlpha() * rightWeight);
        return new Color(red, green, blue);
    }
}
