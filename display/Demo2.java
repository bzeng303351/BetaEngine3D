package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Demo2 extends Frame {
    public BufferedImage image;

    public Demo2(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 100, this);
    }

    public static void main(String[] args) {
        BufferedImage img = null;
        File file = new File("/Users/zeng/IdeaProjects/BetaEngine3D/src/map/sampleImage2.jpg");
        System.out.println(file.canRead());
        try {
            img = ImageIO.read(file);
        } catch (IOException ignored) {
            System.out.println("ioexception");
        }
        Demo2 demo = new Demo2(img);
        demo.setSize(400, 400);
        demo.setVisible(true);
    }
}
