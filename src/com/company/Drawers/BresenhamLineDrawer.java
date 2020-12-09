package com.company.Drawers;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

import static java.lang.StrictMath.abs;

public class BresenhamLineDrawer implements LineDrawer {

    private PixelDrawer pixelDrawer;

    public BresenhamLineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        drawBresenhamLine(x1, y1, x2, y2);
    }

    private void drawBresenhamLine(int x1, int y1, int x2, int y2) {

        int x, y, error;
        boolean swap = false;
        if(abs(x2 - x1) < abs(y2 - y1)){
            int temp = x1; x1 = y1; y1 = temp;
            temp = x2; x2 = y2; y2 = temp;
            swap = true;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        int signX = signum(dx);
        int signY = signum(dy);
        dx = dx < 0 ? -dx : dx;
        dy = dy < 0 ? -dy : dy;
        x = x1;
        y = y1;

        error = 2 * dy - dx;
        pixelDrawer.drawPixel(x, y, Color.BLACK);
        for (int i = 0; i < dx; i++) {
            if (error >= 0) {
                x += signX;
                y += signY;
                error += 2 * (dy - dx);
            } else {
                x += signX;
                error += 2 * dy;
            }
            if (swap)
                pixelDrawer.drawPixel(y, x, Color.BLACK);
            else
                pixelDrawer.drawPixel(x, y, Color.BLACK);
        }
    }

    private int signum(int x) {
        return x < 0 ? -1 : x > 0 ? 1 : 0;
    }
}
