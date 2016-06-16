package com.medha.listviewdemo;

/**
 * Created by Prathyusha on 2/28/16.
 */
public class Colors {
    String colorName,colorHex;

    public Colors(String colorName, String colorHex) {
        this.colorName = colorName;
        this.colorHex = colorHex;
    }

    @Override
    public String toString() {
        return "Colors " +
                 colorName  ;
    }
}
