package TwoDBirth;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-29
 */

public class TwoDBirthLogic extends Canvas {

    private GraphicsContext context = getGraphicsContext2D();
    private int size;
    private int pixelSize;
    private Cell[][] displayGrid;
    private Cell[][] currCellGrid;

    public TwoDBirthLogic(int size, int pixelSize) {
        this.size = size;
        this.pixelSize = pixelSize;

        setHeight(size * pixelSize);
        setWidth(size * pixelSize);

        currCellGrid = new Cell[size][size];
        displayGrid = new Cell[size][size];

        Random random = new Random();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y == 1 && x == 1) {
                    currCellGrid[y][x] = new Cell(1.0F);
                    displayGrid[y][x] = new Cell(1.0F);
                } else {
                    int rand = random.nextInt(8);
                    if (rand == 2) {
                        currCellGrid[y][x] = new Cell(1.0F);
                        displayGrid[y][x] = new Cell(1.0F);
                    } else {
                        currCellGrid[y][x] = new Cell();
                        displayGrid[y][x] = new Cell();
                    }
                }
            }
        }

        currCellGrid[1][1] = new Cell(1.0F);
        displayGrid[1][1] = new Cell(1.0F);

        draw();

        run();
    }

    public void run() {
        new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }

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

                        displayGrid[y][x] = null;
                        displayGrid[y][x] = calculate(cells, own);
                    }
                }

                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        currCellGrid[y][x].setValue(displayGrid[y][x].getValue());
                        currCellGrid[y][x].setState(displayGrid[y][x].getState());
                    }
                }

                Platform.runLater(this::draw);
            }
        }).start();
    }

    private void draw() {
        context.clearRect(0, 0, getWidth(), getHeight());
        for (int y = 1; y < displayGrid.length - 1; y++) {
            for (int x = 1; x < displayGrid[y].length - 1; x++) {
                if (displayGrid[y][x].getValue() != 0.0 || displayGrid[y][x].getState() == 2) {
                    context.setFill(LifeColors.getRgb(displayGrid[y][x].getState()));
                    context.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                }
            }
        }
        context.save();
    }

    private Cell calculate(Cell[][] cells, Cell midCell) {
        Cell returnCell = new Cell();
        returnCell.setValue(midCell.getValue());
        returnCell.setState(midCell.getState());

        int surround = getSurround(cells);


        if (returnCell.getValue() == 1.0) {
            if (surround >= 4) {
                returnCell.setValue(0.0F);
                returnCell.setStateValue(returnCell.getValue());
            } else if (surround <= 1) {
                returnCell.setValue(0.0F);
                returnCell.setStateValue(returnCell.getValue());
            } else {
                returnCell.makeNeutral();
            }
        } else {
            if (surround == 3) {
                returnCell.setValue(1.0F);
                returnCell.setStateValue(returnCell.getValue());
            } else {
                returnCell.makeNeutral();
            }
        }

        return returnCell;
    }

    private int getSurround(Cell[][] cells) {
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
