package OneD;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-28
 */

public class OneDLogic extends Canvas {

    private GraphicsContext context = getGraphicsContext2D();
    private int size;
    private int pixelSize;
    private int[] inRuleSet;
    private boolean[] ruleSet;
    private boolean[] currCellGrid;
    private boolean[][] displayGrid;

    public OneDLogic(int size, int pixelSize) {
        this.size = size;
        this.pixelSize = pixelSize;

        setHeight(size * pixelSize);
        setWidth(size * pixelSize);
        
        currCellGrid = new boolean[size];
        displayGrid = new boolean[size][size];

        Arrays.fill(currCellGrid, false);
        currCellGrid[(size / 2)] = true;

        for (boolean[] booleans : displayGrid) {
            Arrays.fill(booleans, false);
        }

        //inRuleSet = new int[]{0, 0, 0, 1, 1, 1, 0, 0}; //rule 28
        //inRuleSet = new int[]{0, 0, 0, 1, 1, 1, 1, 0}; //rule 30 --> cool
        //inRuleSet = new int[]{0, 0, 1, 1, 0, 0, 1, 0}; //rule 50
        //inRuleSet = new int[]{0, 0, 1, 1, 0, 1, 1, 0}; //rule 54
        //inRuleSet = new int[]{0, 0, 1, 1, 1, 1, 0, 0}; //rule 60
        //inRuleSet = new int[]{0, 1, 0, 0, 1, 0, 0, 1}; //rule 73 --> cool
        //inRuleSet = new int[]{0, 1, 0, 0, 1, 0, 1, 1}; //rule 75
        //inRuleSet = new int[]{0, 1, 0, 1, 0, 1, 1, 0}; //rule 86
        //inRuleSet = new int[]{0, 1, 0, 1, 1, 0, 1, 0}; //rule 90
        //inRuleSet = new int[]{0, 1, 0, 1, 1, 1, 1, 0}; //rule 94
        //inRuleSet = new int[]{0, 1, 1, 0, 0, 1, 1, 0}; //rule 102
        inRuleSet = new int[]{0, 1, 1, 0, 1, 1, 1, 0}; //rule 110 --> cool
        //inRuleSet = new int[]{1, 0, 0, 1, 0, 1, 1, 0}; //rule 150
        //inRuleSet = new int[]{1, 0, 0, 1, 1, 1, 1, 0}; //rule 158
        //inRuleSet = new int[]{1, 0, 1, 1, 1, 1, 0, 0}; //rule 188

        ruleSet = new boolean[inRuleSet.length];
        for (int i = 0; i < inRuleSet.length; i++) {
            if (inRuleSet[i] == 1) ruleSet[i] = true;
            else ruleSet[i] = false;
        }

        draw();

        run();
    }

    public void run() {
        new Thread(() -> {
            while (true){
                boolean[] newCellGrid = new boolean[size];

                for (int i = 0; i < currCellGrid.length; i++) {

                    boolean left;
                    boolean mid;
                    boolean right;

                    if (i == 0){
                        left = currCellGrid[currCellGrid.length - 1];
                        mid = currCellGrid[i];
                        right = currCellGrid[i + 1];
                    } else if (i == currCellGrid.length - 1){
                        left = currCellGrid[i - 1];
                        mid = currCellGrid[i];
                        right = currCellGrid[0];
                    } else {
                        left = currCellGrid[i - 1];
                        mid = currCellGrid[i];
                        right = currCellGrid[i + 1];
                    }

                    newCellGrid[i] = calculate(left, mid, right);
                }

                currCellGrid = newCellGrid;
                Platform.runLater(this::draw);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void draw(){
        moveUp();
        fillLastRow();
        context.clearRect(0,0,getWidth(), getHeight());
        for (int y = 0; y < displayGrid.length; y++) {
            for (int x = 0; x < displayGrid[y].length; x++) {
                if (displayGrid[y][x]){
                    context.setFill(Color.BLACK);
                    context.fillRect(x*pixelSize,y*pixelSize,pixelSize,pixelSize);
                }
            }
        }
        context.save();
    }

    private void moveUp() {
        for (int y = 1; y < displayGrid.length; y++) {
            for (int x = 0; x < displayGrid[y].length; x++) {
                displayGrid[y - 1][x] = displayGrid[y][x];
            }
        }
    }

    private void fillLastRow() {
        for (int x = 0; x < displayGrid[displayGrid.length - 1].length; x++) {
            displayGrid[displayGrid.length - 1][x] = currCellGrid[x];
        }
    }

    private boolean calculate(boolean left, boolean mid, boolean right){
        if (left && mid && right) return ruleSet[0];
        if (left && mid) return ruleSet[1];
        if (left && right) return ruleSet[2];
        if (left) return ruleSet[3];
        if (mid && right) return ruleSet[4];
        if (mid) return ruleSet[5];
        if (right) return ruleSet[6];
        return ruleSet[7];
    }
}
