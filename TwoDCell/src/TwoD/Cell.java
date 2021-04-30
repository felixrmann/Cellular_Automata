package TwoD;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-29
 */

public class Cell {

    private float value;
    private int lifeTime;

    public Cell(){
        value = 0.0F;
    }

    public Cell(float value){
        this.value = value;
        if (value == 1.0) lifeTime = 100;
    }

    public void setValue(float value) {
        this.value = value;
        if (value == 1.0){
            lifeTime = 100;
        }
    }

    public float getValue() {
        return value;
    }

    public void decreaseLifeTime(){
        lifeTime--;
        if (lifeTime <= 0) value = 0.0F;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
