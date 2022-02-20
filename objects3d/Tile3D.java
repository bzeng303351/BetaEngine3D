package objects3d;

import java.util.ArrayList;
import java.util.List;

public class Tile3D {
    public final Vector origin;
    public final int dx;
    public final int dy;
    public final int dz;
    private final Vector xEdge;
    private final Vector yEdge;
    private final Vector zEdge;
    private final Rectangle3D top;
    private final Rectangle3D bottom;
    private final Rectangle3D front;
    private final Rectangle3D back;
    private final Rectangle3D left;
    private final Rectangle3D right;
    public final List<Rectangle3D> planes;

    public Tile3D(Vector origin, int dx, int dy, int dz) {
        this.origin = origin;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        xEdge = new Vector(dx, 0, 0);
        yEdge = new Vector(0, dy, 0);
        zEdge = new Vector(0, 0, dz);
        top = new Rectangle3D(origin.add(zEdge), xEdge, yEdge);
        bottom = new Rectangle3D(origin.add(yEdge), xEdge, yEdge.reverse());
        front = new Rectangle3D(origin.add(xEdge).add(yEdge), xEdge.reverse(), zEdge);
        back = new Rectangle3D(origin, xEdge, yEdge);
        left = new Rectangle3D(origin.add(yEdge), yEdge.reverse(), zEdge);
        right = new Rectangle3D(origin.add(xEdge), yEdge, zEdge);
        planes = new ArrayList<>();
        planes.add(top);
        planes.add(bottom);
        planes.add(front);
        planes.add(back);
        planes.add(left);
        planes.add(right);
    }
}
