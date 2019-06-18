package ru.edu.kolyanpie.controller;

import java.util.ArrayList;
import java.util.List;

import ru.edu.kolyanpie.model.State;

public class WinChecker {
    private static int x, y;
    private static boolean begin, end;
    private static List<List<State>> cellsForCheck;
    private static State current;

    public static boolean checkWin(List<List<State>> cells, int i, int j) {
        System.out.println("checkWin: i = " + i + ", j = " + j);
        current = cells.get(i).get(j);
        if (current.equals(State.EMPTY)) {
            return false;
        }
        begin = false;
        end = false;
        x = cells.size() - 1;
        y = cells.get(0).size() - 1;
        cellsForCheck = new ArrayList<>(x + 1);
        for (int k = 0; k < x + 1; k++) {
            cellsForCheck.add(new ArrayList<>(cells.get(k)));
        }
        return isWin(i, j);
    }

    private static boolean isWin(int i, int j) {
        if (i < 0 || i > x || j < 0 || j > y) {
            return false;
        }
        System.out.println("isWin: i = " + i + ", j = " + j);
        if (!cellsForCheck.get(i).get(j).equals(current)) {
            return false;
        } else if (cellsForCheck.get(i).get(j).equals(State.BLUE)) {
            if (i == 0) {
                begin = true;
            } else if (i == x) {
                end = true;
            }
        } else if (cellsForCheck.get(i).get(j).equals(State.RED)) {
            if (j == 0) {
                begin = true;
            } else if (j == y) {
                end = true;
            }
        }
        if (begin && end) {
            return true;
        }
        cellsForCheck.get(i).set(j, State.EMPTY);
        return isWin(i - 1, j)
                || isWin(i - 1, j + 1)
                || isWin(i, j - 1)
                || isWin(i, j + 1)
                || isWin(i + 1, j)
                || isWin(i + 1, j - 1);
    }


}
