package com.company;

import com.company.Drawers.*;
import com.company.utils.DrawUtils;
import com.company.utils.MyObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseMotionListener, MyObserver {
    private Point position = new Point(0, 0);

    private DrawerEnum type;

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void repaint() {
       super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bf = new BufferedImage(
                getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics gr = bf.createGraphics();

        //LineDrawer ld = new WuLineDrawer(pixelDrawer);

        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.BLACK);

        PixelDrawer pixelDrawer = new GraphicsPixelDrawer(gr);
        DrawerFactory factory = new DrawerFactory(pixelDrawer);
        LineDrawer ld = null;
        try {
            ld = factory.createLineDrawer(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        actualDraw(ld);

        g.drawImage(bf, 0, 0, null);
        gr.dispose();
    }
    private void actualDraw(LineDrawer ld){
        DrawUtils.drawSnowflake(ld, getWidth() / 2,  getHeight() / 2, 80, 60);
        ld.drawLine(getWidth() / 2,  getHeight() / 2,
                position.x, position.y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();
    }

    public MyObserver getListener() {
        return this::typeChanged;
    }

    @Override
    public void typeChanged(DrawerEnum type) {
        this.type = type;
    }
}
