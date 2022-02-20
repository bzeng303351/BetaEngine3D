package objects3d;

import matrix.Matrix;

public class Vector {
    public final int x;
    public final int y;
    public final int z;
    public final int mod;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.mod = (int) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector(int[] m) {
        this(m[0], m[1], m[2]);
    }

    public Vector(int[][] m) {
        this(m[0][0], m[1][0], m[2][0]);
    }

    public Vector shiftOriginTo(Vector newOrigin) {
        return new Vector(x - newOrigin.x, y - newOrigin.y, z - newOrigin.z);
    }

    public Vector add(Vector that) {
        return new Vector(x + that.x, y + that.y, z + that.z);
    }

    public Vector scale(double s) {
        return new Vector((int) (x * s), (int) (y * s), (int) (z * s));
    }

    public Vector reverse() {
        return new Vector(-x, -y, -z);
    }

    public int dot(Vector that) {
        return Matrix.dotProduct(this, that);
    }

    public Vector cross(Vector that) {
        return Matrix.crossProduct(this, that);
    }

    public int[][] toArray() {
        int[][] ret = new int[3][1];
        ret[0][0] = x;
        ret[1][0] = y;
        ret[2][0] = z;
        return ret;
    }
}
