package com.company.Drawers;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.round;

/**
 *
 * Реализация алгоритма Ву для рисования отрезков с применением сглаживания
 */
public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public WuLineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    /**
     * Публичный метод для рисования линии по алгоритму Ву
     * @param x1 координата x начала отрезка
     * @param y1 координата y начала отрезка
     * @param x2 координата x конца отрезка
     * @pa77ram y2 координата y конца отрезка
     */
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        drawXiaolinWuLine(x1, y1, x2, y2);
    }

    private void drawXiaolinWuLine(int x1, int y1, int x2, int y2) {

        boolean swap = false;
        //"переворачиваем" координаты(x <-> y)
        if(abs(x2 - x1) < abs(y2 - y1)){
            int temp = x1; x1 = y1; y1 = temp;
            temp = x2; x2 = y2; y2 = temp;
            swap = true;
        }

        if (x2 < x1){
            int temp = x1; x1 = x2; x2 = temp;
            temp = y1; y1 = y2; y2 = temp;
        }

        float dx = x2 - x1;
        float dy = y2 - y1;

        // горизонтальные и вертикальные линии рисуются отдельно
        if (dx == 0) {
            drawVHLine(x1, y1, x2, y2);
            return;
        }

        // первый пиксель
        if(swap)
            pixelDrawer.drawPixel(y1, x1, Color.BLACK);
        else
            pixelDrawer.drawPixel(x1, y1, Color.BLACK);

        float k = dy / dx;
        float newCoord = y1 + k;

        for (int i = x1 + 1; i < x2; i++) {
            if (swap) {
                pixelDrawer.drawPixel((int) newCoord, i, new Color(0, 0, 0, (int) (255 * (1 - fractionalPart(newCoord)))));
                pixelDrawer.drawPixel((int) newCoord + 1, i, new Color(0, 0, 0, (int) (255 * fractionalPart(newCoord))));
            } else {
                pixelDrawer.drawPixel(i, (int) newCoord, new Color(0, 0, 0, (int) (255 * (1 - fractionalPart(newCoord)))));
                pixelDrawer.drawPixel(i, (int) newCoord + 1, new Color(0, 0, 0, (int) (255 * fractionalPart(newCoord))));
            }
            newCoord += k;
        }

        //последний пиксель
        if(swap)
            pixelDrawer.drawPixel(y2, x2, Color.BLACK);
        else
            pixelDrawer.drawPixel(x2, y2, Color.BLACK);
    }

    private void drawVHLine(int x1, int y1, int x2, int y2) {
        if(x1 == x2){
            for (int y = y1; y < y2; y++) {
                pixelDrawer.drawPixel(x1, y, Color.BLACK);
            }
        }else{
            for (int x = x1; x < x2; x++) {
                pixelDrawer.drawPixel(x, y1, Color.BLACK);
            }
        }
    }

    private float fractionalPart(float x) {
        return x - (int) x;
    }
}
