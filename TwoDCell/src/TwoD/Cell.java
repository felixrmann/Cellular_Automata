package TwoD;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-29
 */

public class Cell {

    private float value;
    private int lifeTime;
    private final byte probability;

    public Cell(){
        value = 0.0F;
        probability = 100;
    }

    public Cell(float value, byte probability){
        this.value = value;
        this.probability = probability;
        if (value == 1.0) lifeTime = 10;
    }

    public void setValue(float value) {
        this.value = value;
        if (value == 1.0){
            lifeTime = 10;
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

    public byte getProbability() {
        return probability;
    }
}
