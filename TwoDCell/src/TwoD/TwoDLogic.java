package TwoD;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-29
 */

public class TwoDLogic extends Canvas {

    private GraphicsContext context = getGraphicsContext2D();
    private int size;
    private int pixelSize;
    private int moves;
    private Cell[][] displayGrid;
    private Cell[][] currCellGrid;

    public TwoDLogic(int size, int pixelSize){
        this.size = size;
        this.pixelSize = pixelSize;

        setHeight(size * pixelSize);
        setWidth(size * pixelSize);

        currCellGrid = new Cell[size][size];
        displayGrid = new Cell[size][size];

        Random random = new Random();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y == 1 && x == 1){
                    currCellGrid[y][x] = new Cell(1.0F, (byte) 100);
                    displayGrid[y][x] = new Cell(1.0F, (byte) 100);
                } else {
                    int rand = random.nextInt(8);
                    if (rand == 2){
                        currCellGrid[y][x] = new Cell(1.0F, (byte) 100);
                        displayGrid[y][x] = new Cell(1.0F, (byte) 100);
                    } else {
                        currCellGrid[y][x] = new Cell();
                        displayGrid[y][x] = new Cell();
                    }
                }
            }
        }

        draw();

        run();
    }

    public void run(){
        new Thread(() -> {
            while (true){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored){}

                for (int y = 1; y < size - 1; y++) {
                    for (int x = 1; x < size - 1; x++) {

                        Cell oneOne = currCellGrid[y - 1][x - 1];
                        Cell oneTwo = currCellGrid[y - 1][x];
                        Cell oneTre = currCellGrid[y - 1][x + 1];
                        Cell twoOne = currCellGrid[y][x - 1];
                        Cell twoTre = currCellGrid[y][x + 1];
                        Cell treOne = currCellGrid[y + 1][x - 1];
                        Cell treTwo = currCellGrid[y + 1][x];
                        Cell treTre = currCellGrid[y + 1][x + 1];

                        Cell own = currCellGrid[y][x];

                        Cell[][] cells = new Cell[][]{
                                {oneOne, oneTwo, oneTre},
                                {twoOne, own, twoTre},
                                {treOne, treTwo, treTre},
                        };

                        displayGrid[y][x] = calculate(cells, own);
                    }
                }

                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        currCellGrid[y][x].setValue(displayGrid[y][x].getValue());
                        currCellGrid[y][x].setLifeTime(displayGrid[y][x].getLifeTime());
                    }
                }

                Platform.runLater(this::draw);
            }
        }).start();
    }

    private void draw(){
        context.clearRect(0,0,getWidth(), getHeight());
        for (int y = 1; y < displayGrid.length - 1; y++) {
            for (int x = 1; x < displayGrid[y].length - 1; x++) {
                if (displayGrid[y][x].getValue() != 0.0){
                    context.setFill(LifeColors.getRgb(displayGrid[y][x].getLifeTime()));
                    context.fillRect(x*pixelSize,y*pixelSize,pixelSize,pixelSize);
                }
            }
        }
        context.save();
    }

    private Cell calculate(Cell[][] cells, Cell midCell){
        Cell returnCell = new Cell();
        returnCell.setValue(midCell.getValue());
        if (returnCell.getValue() == 1.0){
            returnCell.setLifeTime(midCell.getLifeTime());
            returnCell.decreaseLifeTime();
        }

        int surround = getSurround(cells);

        if (surround != 0.0){
            if (returnCell.getValue() == 1.0){
                if (surround >= 4) {
                    returnCell.setValue(0.0F);
                } else if (surround <= 1) {
                    returnCell.setValue(0.0F);
                }
            } else {
                if (surround == 3) {
                    returnCell.setValue(1.0F);
                }
            }
        }

        return returnCell;
    }

    private int getSurround(Cell[][] cells){
        int cnt = 0;
        if (cells[0][0].getValue() == 1.0) cnt++;
        if (cells[0][1].getValue() == 1.0) cnt++;
        if (cells[0][2].getValue() == 1.0) cnt++;
        if (cells[1][0].getValue() == 1.0) cnt++;
        if (cells[1][2].getValue() == 1.0) cnt++;
        if (cells[2][0].getValue() == 1.0) cnt++;
        if (cells[2][1].getValue() == 1.0) cnt++;
        if (cells[2][2].getValue() == 1.0) cnt++;
        return cnt;
    }
}
