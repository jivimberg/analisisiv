package model.functions;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Jul 20, 2010
 * Time: 12:01:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static List<Point2D.Double> createPoints() {
        List<Point2D.Double> points = new ArrayList<Point2D.Double>();
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(2, 2));
        points.add(new Point2D.Double(3, 5));
        points.add(new Point2D.Double(4, 6));
        return points;
    }

}
