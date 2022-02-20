package matrix;

import objects3d.Vector;

public class Matrix {

    public static int dotProduct(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector crossProduct(Vector v1, Vector v2) {
        return new Vector(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public static double[][] multiply(double[][] first, double[][] second) {
        double[][] ret = new double[first.length][second[0].length];
        for (int row = 0; row < first.length; row++)
            for (int col = 0; col < second[0].length; col++) {
                double tempSum = 0;
                for (int i = 0; i < first[0].length; i++)
                    tempSum += first[row][i] * second[i][col];
                ret[row][col] = tempSum;
            }
        return ret;
    }

    private static int[][] toIntArray(double[][] m) {
        int[][] ret = new int[m.length][];
        for (int row = 0; row < m.length; row++) {
            ret[row] = new int[m[row].length];
            for (int col = 0; col < m[row].length; col++)
                ret[row][col] = (int) m[row][col];
        }
        return ret;
    }

    private static double[][] toDoubleArray(int[][] m) {
        double[][] ret = new double[m.length][];
        for (int row = 0; row < m.length; row++) {
            ret[row] = new double[m[row].length];
            for (int col = 0; col < m[row].length; col++)
                ret[row][col] = m[row][col];
        }
        return ret;
    }

    public static Vector rotateX(Vector v, double rad) {
        double[][] m = {{1, 0, 0},
                {0, Math.cos(rad), -1 * Math.sin(rad)},
                {0, Math.sin(rad), Math.cos(rad)}};
        return new Vector(toIntArray(multiply(m, toDoubleArray(v.toArray()))));
    }

    public static Vector rotateY(Vector v, double rad) {
        double[][] m = {{Math.cos(rad), 0, Math.sin(rad)},
                {0, 1, 0},
                {-1 * Math.sin(rad), 0, Math.cos(rad)}};
        return new Vector(toIntArray(multiply(m, toDoubleArray(v.toArray()))));
    }

    public static Vector rotateZ(Vector v, double rad) {
        double[][] m = {{Math.cos(rad), -1 * Math.sin(rad), 0},
                {Math.sin(rad), Math.cos(rad), 0},
                {0, 0, 1}};
        return new Vector(toIntArray(multiply(m, toDoubleArray(v.toArray()))));
    }


}
