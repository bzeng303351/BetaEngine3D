package display;

import map.World3D;
import matrix.Matrix;
import objects3d.Rectangle3D;
import objects3d.Tile3D;
import objects3d.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Camera {
    public Vector origin;
    public Vector normal;
    public Vector horizontal;
    public Vector vertical;
    private double viewAngleDeg;
    private final BufferedImage image;

    public Camera(double viewAngleDeg, BufferedImage image) {
        this.viewAngleDeg = viewAngleDeg;
        this.image = image;
        int zDist = (int) (1.0 / Math.tan(Math.PI * viewAngleDeg / 360) * image.getWidth() / 2);
        origin = new Vector(0, 0, 0);
        normal = new Vector(0, 0, zDist);
        horizontal = new Vector(image.getWidth() / 2, 0, 0);
        vertical = new Vector(0, image.getHeight() / 2, 0);
    }

    public void rotateX(double angleRad) {
        // ToDo: this is wrong implementation, should rotate around horizontal axis.
        normal = Matrix.rotateX(normal, angleRad);
        horizontal = Matrix.rotateX(horizontal, angleRad);
        vertical = Matrix.rotateX(vertical, angleRad);
    }

    public void rotateY(double angleRad) {
        // ToDo: this is wrong implementation, should rotate around horizontal axis.
        normal = Matrix.rotateY(normal, angleRad);
        horizontal = Matrix.rotateY(horizontal, angleRad);
        vertical = Matrix.rotateY(vertical, angleRad);
    }

    public void move(int step) {
        origin = new Vector(
                origin.x + step * normal.x / normal.mod,
                origin.y + step * normal.y / normal.mod,
                origin.z + step * normal.z / normal.mod
        );
    }

    public void render(World3D world) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                int sx = j - width/2;
                int sy = height/2 - i;
                Vector ray = new Vector(
                        normal.x + sx * horizontal.x / horizontal.mod + sy * vertical.x / vertical.mod,
                        normal.y + sx * horizontal.y / horizontal.mod + sy * vertical.y / vertical.mod,
                        normal.z + sx * horizontal.z / horizontal.mod + sy * vertical.z / vertical.mod
                );
                image.setRGB(j, i, getColor(ray, world));
            }
    }

    private int getColor(Vector ray, World3D world) {
        Integer minDist = Integer.MAX_VALUE;
        int color = Color.GREEN.getRGB();
        for (Tile3D t : world.world) {
            for (Rectangle3D p : t.planes) {
                if (p.rayInRectangle(this, ray)) {
                    int dist = p.intersectPointInRectangle(this, ray).add(p.origin).mod;
                    if (dist < minDist) {
                        color = p.getRGB(this, ray);
                        minDist = dist;
                    }
                }
            }
        }
        return color;
    }


}
