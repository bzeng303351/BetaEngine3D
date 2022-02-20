package objects3d;

import display.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rectangle3D {

    public final Vector origin;
    public final Vector xEdge;
    public final Vector yEdge;
    private final Vector normal;
    // I want to use z as height
    // x and y as the normal way in math, i.e. left bottom corner is origin
    // to the right is x, to the up is y.
    // a b c d in clockwise
    private final Vector a;
    private final Vector b;
    private final Vector c;
    private final Vector d;

    private BufferedImage image;

    public Rectangle3D(Vector origin, Vector xEdge, Vector yEdge) {
        this.origin = origin;
        this.xEdge = xEdge;
        this.yEdge = yEdge;
        this.normal = xEdge.cross(yEdge).scale(1.0 / xEdge.mod);
        a = origin;
        b = origin.add(yEdge);
        c = b.add(xEdge);
        d = origin.add(xEdge);
        image = new BufferedImage(xEdge.mod, yEdge.mod, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++) {
                if (i <= 50 || i >=image.getWidth() - 50 || j <= 50 || j > image.getHeight() - 50)
                    image.setRGB(i, j, Color.YELLOW.getRGB());
                else
                    image.setRGB(i, j, Color.BLUE.getRGB());
            }
    }

    public boolean rayInRectangle(Camera camera, Vector ray) {
        Vector newA = a.shiftOriginTo(camera.origin);
        Vector newB = b.shiftOriginTo(camera.origin);
        Vector newC = c.shiftOriginTo(camera.origin);
        Vector newD = d.shiftOriginTo(camera.origin);
        if (!(inFront(camera.normal, newA) && inFront(camera.normal, newB)
        && inFront(camera.normal, newC) && inFront(camera.normal, newD)))
            return false;
        if (abovePlane(newA, newB, ray) && abovePlane(newB, newC, ray)
        && abovePlane(newC, newD, ray) && abovePlane(newD, newA, ray))
            return true;
        else
            return false;
    }

    public Vector intersectPointInRectangle(Camera camera, Vector ray) {
        double s = (double) origin.shiftOriginTo(camera.origin).dot(normal) / ray.dot(normal);
        return ray.scale(s).shiftOriginTo(origin.shiftOriginTo(camera.origin));
    }

    public int relativeX(Vector vectorInRectangle) {
        int ret = xEdge.dot(vectorInRectangle) / xEdge.mod;
        if (ret < 0)
            ret = 0;
        if (ret >= image.getWidth())
            ret = image.getWidth()-1;
        return ret;
    }

    public int relativeY(Vector vectorInRectangle) {
        int ret = image.getHeight() - yEdge.dot(vectorInRectangle) / yEdge.mod;
        if (ret < 0)
            ret = 0;
        if (ret >= image.getHeight())
            ret = image.getHeight()-1;
        return ret;
    }

    private boolean inFront(Vector normal, Vector that) {
        return normal.dot(that) > 0;
    }

    private boolean abovePlane(Vector planeX, Vector planeY, Vector that) {
        // the scale here makes sure the following calculation won't overflow.
        // I want to use z as height
        // x and y as the normal way in math, i.e. left bottom corner is origin
        // to the right is x, to the up is y.
        return planeX.cross(planeY).scale(1.0 / planeX.mod).dot(that) > 0;
    }

    public int getRGB(Camera camera, Vector ray) {
        return getRGBInPlane(intersectPointInRectangle(camera, ray));
    }

    public int getRGBInPlane(Vector relative) {
        return image.getRGB(relativeX(relative),
                relativeY(relative));
    }

    public void setImage(BufferedImage image) {
        for (int x = 0; x < this.image.getWidth(); x++)
            for (int y = 0; y < this.image.getHeight(); y++)
                this.image.setRGB(x, y, image.getRGB(x*image.getWidth()/this.image.getWidth(), y*image.getWidth()/this.image.getWidth()));
    }
}
