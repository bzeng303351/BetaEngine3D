package map;

import objects3d.Rectangle3D;
import objects3d.Tile3D;
import objects3d.Vector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World3D {

    public final List<Tile3D> world = new ArrayList<>();

    public World3D() throws IOException {
        Vector origin = new Vector(0, 0, 1000);
        Vector v = new Vector(500, 0, 1000);
        Tile3D t = new Tile3D(origin, 400, 400, 400);
        Tile3D t2 = new Tile3D(v, 500, 500, 500);
        world.add(t);
        world.add(t2);
        BufferedImage img = null;
        try {
            // img = ImageIO.read(new File("/Users/zeng/IdeaProjects/BetaEngine3D/src/map/sampleImage2.jpg"));
            img = ImageIO.read(new File("sampleImage2.jpg"));
        } catch (IOException ignored) {
            System.out.println("ioexception");
        }
        System.out.println(img);
        for (Tile3D tile : world)
            for (Rectangle3D p : tile.planes)
                p.setImage(img);

    }
}
