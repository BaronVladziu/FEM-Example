package solver;

public class SystemOfEquations {

    private final int n;
    private double table[][];

    SystemOfEquations(int n) {
        this.n = n;
        this.table = new double[n+1][n];
    }

    public double get(int x, int y) {
        return this.table[x][y];
    }

    void set(int x, int y, double value) {
        this.table[x][y] = value;
    }

    void print() {
        System.out.println('[');
        for (int j = 0; j < this.n; j++) {
            for (int i = 0; i < this.n + 1; i++) {
                System.out.print(this.table[i][j]);
                System.out.print('\t');
            }
            System.out.print('\n');
        }
        System.out.println("]");
    }

    void solve() {
        //Gaussian method
        if (!isEasilySolvable()) {
            throw new RuntimeException("System not easily solvable!");
        }
        for (int j = 0; j < this.n; j++) {
            scaleX(j, 1/this.table[j][j]);
            for (int i = j+1; i < this.n; i++) {
                subtractX(i, j, this.table[j][i]);
            }
        }
        for (int j = this.n - 1; j >= 0; j--) {
            scaleX(j, 1/this.table[j][j]);
            for (int i = j-1; i >= 0; i--) {
                subtractX(i, j, this.table[j][i]);
            }
        }
    }

    boolean isEasilySolvable() {
        for (int i = 0; i < this.n; i++) {
            if (this.table[i][i] == 0) {
                return false;
            }
        }
        return true;
    }

    private void subtractX(int fromY, int posY, double factor) {
        if (factor != 0) {
            for (int i = 0; i < this.n + 1; i++) {
                this.table[i][fromY] -= factor * this.table[i][posY];
            }
        }
    }

    private void scaleX(int posY, double factor) {
        if (factor != 0) {
            for (int i = 0; i < this.n + 1; i++) {
                this.table[i][posY] *= factor;
            }
        }
    }

    void scaleY(int posX, double factor) {
        for (int j = 0; j < this.n; j++) {
            this.table[posX][j] *= factor;
        }
    }

    public int getN() {
        return this.n;
    }

    double getMaxAbsValue() {
        double max = 0;
        double a;
        for (int i = 0; i < this.n + 1; i++) {
            for (int j = 0; j < this.n; j++) {
                a = Math.abs(this.table[i][j]);
                if (a > max) {
                    max = a;
                }
            }
        }
        return max;
    }

    void scale(double factor) {
        for (int j = 0; j < this.n; j++) {
            scaleX(j, factor);
        }
    }

    void swap(int x1, int y1, int x2, int y2) {
        double temp = this.table[x1][y1];
        this.table[x1][y1] = this.table[x2][y2];
        this.table[x2][y2] = temp;
    }


}
