package com.company.Drawers;

import com.company.LineDrawer;
import com.company.PixelDrawer;

public class DrawerFactory {
    public DrawerFactory(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    private PixelDrawer pixelDrawer;

    public LineDrawer createLineDrawer(DrawerEnum type) throws Exception {
        LineDrawer ld;
        switch (type){
            case DDA:
                ld = new DDALineDrawer(pixelDrawer);
                break;
            case WU:
                ld = new WuLineDrawer(pixelDrawer);
                break;
            case BRESENHAM:
                ld = new BresenhamLineDrawer(pixelDrawer);
                break;
            default:
                throw new IllegalArgumentException("Error!");
        }
        return ld;
    }
}
