package TwoDBirth;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-29
 */

public class Cell {

    private float value;
    private int state;

    public Cell(){
        value = 0.0F;
    }

    public Cell(float value){
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void makeNeutral(){
        state = 0;
    }

    public void setStateValue(float value){
        switch ((int) value) {
            case 0 -> state = 2;
            case 1 -> state = 1;
        }
    }
}
