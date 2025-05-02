package DataStructureProject3;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initially graph.txt will be loaded into graph matrix...");
        GraphMatrix graphMatrix = GraphMatrix.load(new File("src/graph.txt"));
        boolean exit = false;
        while(!exit) {
            System.out.println("1. Read graph from file");
            System.out.println("2. Is there a path between two city");
            System.out.println("3. Shortest path between two city");
            System.out.println("4. Number of simple paths between two city");
            System.out.println("5. Neighbours of a city");
            System.out.println("6. Highest degree city");
            System.out.println("7. Does Graph directed");
            System.out.println("8. Does Graph has cycle");
            System.out.println("9. Are cities adjacent");
            System.out.println("10. Vertices of a city");
            System.out.println("11. Breadth first search");
            System.out.println("12. Depth first search");

            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");
            try {
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();
                String city1, city2;
                switch (choice) {
                    case 1:
                        System.out.print("Give file location to read:  ");
                        String fileName = input.next();
                        File file = new File(fileName);
                        graphMatrix = GraphMatrix.load(file);
                        break;
                    case 2:
                        System.out.print("Enter city name 1: ");
                        city1 = input.next();
                        System.out.print("Enter city name 2: ");
                        city2 = input.next();
                        if (graphMatrix.isThereAPath(city1, city2)) {
                            System.out.println("\nThere is a path between " + city1 + " and " + city2 + "\n");
                        } else {
                            System.out.println("\nThere is no path between " + city1 + " and " + city2 + "\n");
                        }
                        break;
                    case 3:
                        System.out.print("Enter city name 1: ");
                        city1 = input.next();
                        System.out.print("Enter city name 2: ");
                        city2 = input.next();
                        System.out.println("\nShortest path between " + city1 + " and " + city2 + " is " + graphMatrix.findShortestPathLength(city1, city2) + "\n");
                        break;
                    case 4:
                        System.out.print("Enter city name 1: ");
                        city1 = input.next();
                        System.out.print("Enter city name 2: ");
                        city2 = input.next();
                        System.out.println("\nNumber of simple paths between " + city1 + " and " + city2 + " is " + graphMatrix.findSimplePathCount(city1, city2) + "\n");
                        break;
                    case 5:
                        System.out.print("Enter city name: ");
                        city1 = input.next();
                        System.out.println("\nNeighbours of " + city1 + " are " + graphMatrix.findNeighbors(city1) + "\n");
                        break;
                    case 6:
                        System.out.println("\nHighest degree city is " + graphMatrix.findVerticesWithHighestDegree() + "\n");
                        break;
                    case 7:
                        if (graphMatrix.isDirected()) {
                            System.out.println("\nGraph is directed\n");
                        } else {
                            System.out.println("\nGraph is not directed\n");
                        }
                        break;
                    case 8:
                        System.out.print("Enter city name: ");
                        city1 = input.next();
                        if (graphMatrix.isThereACycle(city1)) {
                            System.out.println("\nCity " + city1 + " has cycle\n");
                        } else {
                            System.out.println("\nCity " + city1 + " has no cycle\n");
                        }
                        break;
                    case 9:
                        System.out.print("Enter city name 1: ");
                        city1 = input.next();
                        System.out.print("Enter city name 2: ");
                        city2 = input.next();
                        if (graphMatrix.isAdjacent(city1, city2)) {
                            System.out.println("\nCities " + city1 + " and " + city2 + " are adjacent.\n");
                        } else {
                            System.out.println("\nCities " + city1 + " and " + city2 + " are not adjacent.\n");
                        }
                        break;
                    case 10:
                        System.out.print("Enter city name: ");
                        city1 = input.next();
                        System.out.println("\nVertices of " + city1 + " are " + graphMatrix.findTheVerticesCount(city1) + "\n");
                        break;
                    case 11:
                        System.out.println("Enter city 1 name: ");
                        city1 = input.next();
                        System.out.println("Enter city 2 name: ");
                        city2 = input.next();
                        System.out.println("\nBreadth first search of " + city1 + " to " + city2 + " is: \n");
                        graphMatrix.searchWithBreadthFirst(city1, city2);
                        System.out.println("\n");
                        break;
                    case 12:
                        System.out.println("Enter city 1 name: ");
                        city1 = input.next();
                        System.out.println("Enter city 2 name: ");
                        city2 = input.next();
                        System.out.println("\nDepth first search of " + city1 + " to " + city2 + " is: \n");
                        graphMatrix.searchWithDepthFirst(city1, city2);
                        System.out.println();
                        break;
                    case 13:
                        exit = true;
                        break;
                    default:
                        System.out.println("----------------------------------");
                        System.out.println("\nInvalid choice\n");
                        System.out.println("----------------------------------");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.toString());
            }
        }
    }
}