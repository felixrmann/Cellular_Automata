package TwoD;

import javafx.scene.paint.Color;

public enum LifeColors {

    COL10 (Color.rgb(206, 40, 127)),
    COL9 (Color.rgb(185, 34, 126)),
    COL8 (Color.rgb(164, 30, 124)),
    COL7 (Color.rgb(143, 27, 121)),
    COL6 (Color.rgb(122, 25, 116)),
    COL5 (Color.rgb(100, 23, 111)),
    COL4 (Color.rgb(79, 22, 104)),
    COL3 (Color.rgb(57, 20, 96)),
    COL2 (Color.rgb(33, 17, 87)),
    COL1 (Color.rgb(0, 14, 77));

    private Color rgb;

    LifeColors(Color rgb) {
        this.rgb = rgb;
    }

    public static Color getRgb(int number) {
        switch (number){
            case 10 -> {
                return COL10.rgb;
            }
            case 9 -> {
                return COL9.rgb;
            }
            case 8 -> {
                return COL8.rgb;
            }
            case 7 -> {
                return COL7.rgb;
            }
            case 6 -> {
                return COL6.rgb;
            }
            case 5 -> {
                return COL5.rgb;
            }
            case 4 -> {
                return COL4.rgb;
            }
            case 3 -> {
                return COL3.rgb;
            }
            case 2 -> {
                return COL2.rgb;
            }
            case 1 -> {
                return COL1.rgb;
            }
            default -> {
                return null;
            }
        }
    }
}
