package solver;

import java.util.LinkedList;
import java.util.List;

public class Solver {

    public final List<Point> solve(int n, double k) {

        //Create system of equations
        SystemOfEquations system = new SystemOfEquations(n + 1);
        system.set(0, 0, 1);
        for (int j = 1; j < n + 1; j++) {
            for (int i = 0; i < n + 1; i++) {
                system.set(i, j, calcB(i, j, n, k));
            }
            system.set(n + 1, j, calcL(j, n));
        }
        system.print();

        //Solve system of equations
        system.solve();
        system.print();

        //Add shift
        for (int j = 0; j < n + 1; j++) {
            system.set(n + 1, j, system.get(n + 1, j) + 5 - 5*j/(double)n);
        }

        //Create drawable points
        List<Point> points = new LinkedList<>();
        for (int j = 0; j < n + 1; j++) {
            points.add(new Point(j/(double)n, system.get(n + 1, j)));
        }
        return points;
    }

    private double calcB(int i, int j, int n, double k) {
        if (i < 0 || i > n || j < 0 || j > n) {
            throw new RuntimeException("Values of i and j must be between 0 and n!");
        }
        if (i == j) {
            if (i == 0) {
                return -0.5 + k*n;
            } else if (i == n) {
                return 0.5 + k*n;
            } else {
                return 2*k*n;
            }
        } else if (j - i == 1) {
            return -0.5 - k*n;
        } else if (j - i == -1) {
            return 0.5 - k*n;
        } else {
            return 0;
        }
    }

    private double calcL(int i, int n) {
        if (i < 0 || i > n) {
            throw new RuntimeException("Value of i must be between 0 and n!");
        }
        if (i == 0 || i == n) {
            return 5/(double)(2*n) - 7.5;
        } else {
            return 5/(double)n - 7.5;
        }
    }

}
