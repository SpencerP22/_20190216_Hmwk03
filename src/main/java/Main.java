import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //statistics for all shapes in INPUT
        int totalShapesRead = 0;
        double totalAverageArea = 0;
        double largestArea = 0;
        double smallestArea = Integer.MAX_VALUE;

        //statistics for all CIRCLES in INPUT
        int totalCircles = 0;
        double circleAverageArea = 0;
        double largestCircle = 0;
        double smallestCircle = Integer.MAX_VALUE;

        //statistics for all RECTANGLES in INPUT
        int totalRectangles = 0;
        double rectangleAverageArea = 0;
        double largestRectangle = 0;
        double smallestRectangle = Integer.MAX_VALUE;

        try {
            //opens two BufferedWriters; one for the circles in input, the other for the rectangles
            BufferedWriter circleOutput = new BufferedWriter(new FileWriter("circles.txt"));
            BufferedWriter rectangleOutput = new BufferedWriter(new FileWriter("rectangles.txt"));
            Scanner input = new Scanner(new File("input.txt"));

            while (input.hasNextLine()) {
                String line = input.nextLine();
                //splits line on spaces
                String[] parts = line.split("\\s+");
                //second 'if' argument accounts for empty lines so parseDouble does not error out.
                if (parts.length == 1  && !(line.equals(""))) {
                    double radius = Double.parseDouble(parts[0]);
                    double area = calculateArea(radius);
                    //compares the current shape's area against the largest and smallest areas
                    if(area > largestArea)
                        largestArea = area;
                    if(area > largestCircle)
                        largestCircle = area;
                    if(area < smallestArea)
                        smallestArea = area;
                    if(area < smallestCircle)
                        smallestCircle = area;

                    totalAverageArea += area;
                    totalShapesRead++;

                    circleAverageArea += area;
                    totalCircles++;

                    //Writes information about current circle being calculated and writes it to circles.txt
                    String circleString = circleToString(radius);
                    circleOutput.write(circleString);
                    circleOutput.newLine();
                }
                if(parts.length == 2) {
                    double length = Double.parseDouble(parts[0]);
                    double width = Double.parseDouble(parts[1]);
                    double area = calculateArea(length,width);
                    if(area > largestArea)
                        largestArea = area;
                    if(area > largestRectangle)
                        largestRectangle = area;
                    if(area < smallestArea)
                        smallestArea = area;
                    if(area < smallestRectangle)
                        smallestRectangle = area;

                    totalAverageArea+= area;
                    totalShapesRead++;

                    rectangleAverageArea+= area;
                    totalRectangles++;

                    String rectangleString = rectangleToString(length,width);
                    rectangleOutput.write(rectangleString);
                    rectangleOutput.newLine();
                }
            }

            input.close();
            circleOutput.close();
            rectangleOutput.close();
        } catch (FileNotFoundException e) {
            System.err.println("could not open input file");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(totalStats(totalShapesRead, totalAverageArea, largestArea, smallestArea));
        System.out.println(circleStats(totalCircles, circleAverageArea, largestCircle, smallestCircle));
        System.out.println(rectangleStats(totalRectangles, rectangleAverageArea, largestRectangle, smallestRectangle));
    }

    //returns a formatted string for the stats of all shapes
    public static String totalStats(int totalShapesRead, double totalAverageArea, double largestArea, double smallestArea) {
        String s = String.format("---------- Overall Stats ----------\n" +
                        "     Total shapes read: %d\n" +
                        "     Total average area: %f\n" +
                        "     Largest area: %f\n" +
                        "     Smallest area: %f\n",
                        totalShapesRead, totalAverageArea, largestArea, smallestArea);
        return s;
    }
    //returns a formatted string for the stats of all circles
    public static String circleStats(int totalCircles, double totalAverageArea, double largestArea, double smallestArea) {
        String s = String.format("---------- Circle Stats ----------\n" +
                        "     Total circles read: %d\n" +
                        "     Average area: %f\n" +
                        "     Largest circle: %f\n" +
                        "     Smallest circle: %f\n",
                totalCircles, totalAverageArea, largestArea, smallestArea);
        return s;
    }
    //returns a formatted string for the stats of all rectangles
    public static String rectangleStats(int totalRectangles, double totalAverageArea, double largestArea, double smallestArea) {
        String s = String.format("---------- Rectangle Stats ----------\n" +
                        "     Total rectangles read: %d\n" +
                        "     Average area: %f\n" +
                        "     Largest rectangle: %f\n" +
                        "     Smallest rectangle: %f\n",
                totalRectangles, totalAverageArea, largestArea, smallestArea);
        return s;
    }

    //calculates area of a circle
    public static double calculateArea(double radius) {
        double area = 0.0;
        area = Math.PI * radius * radius;
        return area;
    }

    //calculates perimeter of a circle
    public static double calculatePerimeter(double radius) {
        double perimeter = 0.0;
        perimeter = 2 * Math.PI * radius;
        return perimeter;
    }

    //calculates area of a rectangle
    public static double calculateArea(double length, double width) {
        double area = 0.0;
        area = length * width;
        return area;
    }

    //calculates the perimeter of a rectangle
    public static double calculatePerimeter(double length, double width) {
        double perimeter = 0.0;
        perimeter = (2 * length) + (2 * width);
        return perimeter;
    }

    //checks if a rectangle is a square
    public static boolean isSquare(double length, double width){
        boolean result = (Math.abs(length-width) < length*0.0001);
        return result;
    }

    //returns a formatted string for the information of a circle
    public static String circleToString(double radius) {
        String s = String.format("Radius: %1.3f, Area: %1.3f, Perimeter: %1.3f ", radius, (Math.PI * radius * radius),
                (2*Math.PI*radius));
        return s;
    }

    //returns a formatted string for the information of a rectangle
    public static String rectangleToString(double length, double width) {
        String s = String.format("Length: %1.3f, Width: %1.3f, Area: %1.3f, Perimeter: %1.3f ",
                length, width, length*width, 2*length+2*width);
        if(isSquare(length, width))
            s+= " (Square)";
        return s;
        }
    }

