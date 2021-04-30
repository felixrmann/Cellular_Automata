package TwoDBirth;

import javafx.scene.paint.Color;

public enum LifeColors {

    COL0(Color.rgb(84, 84, 84)),
    COL1(Color.rgb(79, 226, 240)),
    COL2(Color.rgb(235, 97, 52));

    private Color rgb;

    LifeColors(Color rgb) {
        this.rgb = rgb;
    }

    public static Color getRgb(int number) {
        switch (number){
            case 0: {
                return COL0.rgb;
            }
            case 1: {
                return COL1.rgb;
            }
            case 2: {
                return COL2.rgb;
            }
            default: return COL0.rgb;
        }
    }
}
