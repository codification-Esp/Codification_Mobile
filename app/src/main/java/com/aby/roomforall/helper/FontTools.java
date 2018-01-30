package com.aby.roomforall.helper;

import android.content.Context;
import android.graphics.Typeface;


public class FontTools {

    private FontTools() {}

    private static FontTools instance = null;

    public static FontTools getInstance() {
        if (instance == null) {
            synchronized (FontTools.class) {
                if (instance == null) instance = new FontTools();
            }
        }
        return instance;
    }

    public Typeface getFont(String font, Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/"+font+".ttf");
    }
}
