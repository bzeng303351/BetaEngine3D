package objects3d;

public class Rectangle3Dv2 {
    public final Vector origin;
    public final Vector xEdge; // this will be a unit vector
    public final Vector yEdge; // this will be a unit vector
    private final Vector normal;

    public Rectangle3Dv2(Vector origin, Vector xEdge, Vector yEdge) {
        this.origin = origin;
        this.xEdge = xEdge;
        this.yEdge = yEdge;
        this.normal = xEdge.cross(yEdge);
    }
}
