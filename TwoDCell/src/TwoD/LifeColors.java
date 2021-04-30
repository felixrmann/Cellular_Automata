package TwoD;

import javafx.scene.paint.Color;

public enum LifeColors {

    COL10 (Color.rgb(255, 0, 0)),
    COL9 (Color.rgb(230, 0, 0)),
    COL8 (Color.rgb(204, 0, 0)),
    COL7 (Color.rgb(179, 0, 0)),
    COL6 (Color.rgb(153, 0, 0)),
    COL5 (Color.rgb(128, 0, 0)),
    COL4 (Color.rgb(102, 0, 0)),
    COL3 (Color.rgb(77, 0, 0)),
    COL2 (Color.rgb(51, 0, 0)),
    COL1 (Color.rgb(26, 0, 0));

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
