package display;

import map.World3D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Demo extends Frame implements KeyListener {

    public BufferedImage bi;
    public Camera camera;
    public World3D world;
    public double rotateStep = 0.1;
    public int moveStep = 50;

    public Demo(int width, int height, Camera camera, World3D world, BufferedImage image) {
        bi = image;
        this.camera = camera;
        this.world = world;
        this.camera.render(world);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bi, 0, 100, this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            camera.rotateX(rotateStep);
            camera.render(world);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            camera.rotateX(-1.0 * rotateStep);
            camera.render(world);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            camera.rotateY(rotateStep);
            camera.render(world);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            camera.rotateY(-1.0 * rotateStep);
            camera.render(world);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            camera.move(moveStep);
            camera.render(world);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            camera.move(-1 * moveStep);
            camera.render(world);
            repaint();
        }
    }
    public static void main(String[] args) throws IOException {
        int width = 400;
        int height = 400;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Camera camera = new Camera(60, image);
        World3D world = new World3D();
        Demo demo = new Demo(width, height, camera, world, image);
        demo.setSize(width, height);
        demo.addKeyListener(demo);
        demo.setVisible(true);

    }
}
