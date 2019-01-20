package app;

import org.jfree.ui.RefineryUtilities;
import plotter.Figure;
import solver.Point;
import solver.Solver;

import java.util.List;

public class App {

    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("Usage:\n" +
                    "arg0 - n\n" +
                    "arg1 - k\n\n" +
                    "Example:\n" +
                    "main 100 1");
            return;
        }
        Solver solver = new Solver();
        List<Point> points = solver.solve(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Figure figure = new Figure("XY Series Demo", "Random Data", points);

        figure.pack();
        RefineryUtilities.centerFrameOnScreen(figure);
        figure.setVisible(true);
    }

}
