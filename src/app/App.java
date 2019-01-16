package app;

import org.jfree.ui.RefineryUtilities;
import plotter.Figure;
import solver.Point;
import solver.Solver;

import java.util.List;

public class App {

    public static void main(final String[] args) {
        Solver solver = new Solver();
        List<Point> points = solver.solve(10, 1);
        Figure figure = new Figure("XY Series Demo", "Random Data", points);

        figure.pack();
        RefineryUtilities.centerFrameOnScreen(figure);
        figure.setVisible(true);
    }

}
