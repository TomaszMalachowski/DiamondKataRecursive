package codingdojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Diamond {

    private static final Point FIRST_POINT = new Point('A');
    private final Point middlePoint;

    public Diamond(char middlePoint) {
        this.middlePoint = new Point(middlePoint);
    }

    public String print() {
        if (FIRST_POINT.equals(middlePoint)) {
            return "A\n";
        }
        return printUpper(new StringBuffer(), FIRST_POINT);

    }

    private String printUpper(StringBuffer result, Point point) {
        result.append(asLine(point));
        if (point.isBefore(middlePoint)) {
            return printUpper(result, point.next());
        } else {
            return printLower(result, point.previous());
        }
    }

    private String printLower(StringBuffer result, Point point) {
        result.append(asLine(point));
        if (point.equals(FIRST_POINT)) {
            return result.toString();
        }
        return printLower(result, point.previous());
    }

    private String asLine(Point point) {
        return outerIndentation(point) + innerDiamond(point) + "\n";
    }

    private String innerDiamond(Point point) {
        if (point.equals(FIRST_POINT)) {
            return FIRST_POINT.toString();
        }
        return point + innerIndentation(point) + point;
    }

    private String outerIndentation(Point point) {
        return spaces(middlePoint.distanceFrom(point));
    }

    private String innerIndentation(Point point) {
        return spaces(point.innerDistance());
    }

    private static String spaces(int i) {
        return new String(new char[i]).replace('\0', ' ');
    }

    private static class Point {
        private final int number;

        public Point(final int number) {
            this.number = number;
        }

        public int number() {
            return number;
        }

        public int distanceFrom(Point currentPoint) {
            return this.number - currentPoint.number;
        }

        private int innerDistance() {
            return 1 + 2 * (number() - FIRST_POINT.number() - 1);
        }

        public boolean isBefore(Point middleLetter) {
            return this.number < middleLetter.number;
        }

        public Point next() {
            return new Point(number + 1);
        }

        public Point previous() {
            return new Point(number - 1);
        }

        @Override
        public String toString() {
            return String.valueOf((char) number);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return new EqualsBuilder().append(number, point.number).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(number).toHashCode();
        }
    }
}
