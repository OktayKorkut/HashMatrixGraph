package DataStructureProject3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphMatrix {
    public int[][] edges;
    public int numV;

    public LinearProbingHash<String> linearProbingHash;

    public GraphMatrix(int V) {
            numV = V;
            edges = new int[V][V];
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    edges[i][j] = 0;
                }
            }

            LinearProbingHash<String> hash = new LinearProbingHash<>(V);
            linearProbingHash = hash;
        }

    static GraphMatrix load(File file){
            GraphMatrix graphMatrix = new GraphMatrix(500);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] fromTos = line.split(" -> ");
                    String from = fromTos[0];
                    String[] toWeight = fromTos[1].split(", ");
                    for (int i = 0; i < toWeight.length; i++) {
                        String[] toWeightParts = toWeight[i].split(": ");
                        String to = toWeightParts[0];
                        int weight = Integer.parseInt(toWeightParts[1]);
                        graphMatrix.insertEdge(from, to, weight);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("-----------------");
                System.out.println("File not found");
                System.out.println("-----------------");
            }

            return graphMatrix;
        }

    public void insertEdge(String a, String b, int weight) {
            int from = linearProbingHash.insert(a);
            int to = linearProbingHash.insert(b);

            edges[from][to] = weight;
    }

    public boolean isThereAPath(String v1, String v2) {
        int v1Index = linearProbingHash.findIndexOfElement(v1);
        int v2Index = linearProbingHash.findIndexOfElement(v2);
        boolean[] visited = new boolean[numV];
        ArrayList<Integer> path = new ArrayList<>();

        visited[v1Index] = true;
        path.add(v1Index);

        return isThereAPathRec(v1Index, v2Index, visited, path);
    }

    private boolean isThereAPathRec(int v1Index, int v2Index, boolean[] visited, ArrayList<Integer> path) {
        visited[v1Index] = true;
        path.add(v1Index);
        if (v1Index == v2Index) {
            return true;
        }
        for (int i = 0; i < numV; i++) {
            if (edges[v1Index][i] != 0 && !visited[i]) {
                if (isThereAPathRec(i, v2Index, visited, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size()-1);
        return false;
    }

    public void searchWithBreadthFirst(String v1, String v2) {
        int v1Index = linearProbingHash.findIndexOfElement(v1);
        int v2Index = linearProbingHash.findIndexOfElement(v2);
        boolean[] visited = new boolean[numV];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(v1Index);
        visited[v1Index] = true;

        while(!path.isEmpty()){
            int source = path.getFirst();
            path.remove(0);

            if (source == v2Index) {
                System.out.println(linearProbingHash.findElementByHashCode(source));
                System.out.println( "Found. \n" );
                return;
            }

            System.out.print(linearProbingHash.findElementByHashCode(source) + "->");


            ArrayList<Integer> neighbors = neighborsArray(source);

            for (int neighbor:neighbors) {
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    path.add(neighbor);
                }
            }
        }
    }

    public void searchWithDepthFirst(String v1, String v2) {
        int v1Index = linearProbingHash.findIndexOfElement(v1);
        int v2Index = linearProbingHash.findIndexOfElement(v2);
        boolean[] visited = new boolean[numV];
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> neigbors = neighborsArray(v1Index);

        visited[v1Index] = true;
        path.add(v1Index);

        searchWithDepthFirstRec(v1Index, v2Index, visited, path, neigbors);
    }

    private void searchWithDepthFirstRec(int v1Index, int v2Index, boolean[] visited, ArrayList<Integer> path, ArrayList<Integer> neigbors) {
        if (v1Index == v2Index) {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(linearProbingHash.findElementByHashCode(path.get(i)));
                if(i != path.size()-1)
                    System.out.print(" -> ");
            }
            System.out.println();
            return;
        }

        for (int indexOfNeighbor:neigbors) {
            if (edges[v1Index][indexOfNeighbor] != 0 && !visited[indexOfNeighbor]) {
                visited[indexOfNeighbor] = true;
                path.add(indexOfNeighbor);
                searchWithDepthFirstRec(indexOfNeighbor, v2Index, visited, path, neighborsArray(indexOfNeighbor));
            }
        }

    }

    public int findShortestPathLength(String v1, String v2) {
        int v1Index = linearProbingHash.findIndexOfElement(v1);
        int v2Index = linearProbingHash.findIndexOfElement(v2);
        boolean[] visited = new boolean[numV];
        ArrayList<Integer> path = new ArrayList<>();
        int pathLength = 0;

        visited[v1Index] = true;
        path.add(v1Index);

        while(!path.isEmpty()){
            int current = path.get(0);
            System.out.print(linearProbingHash.findElementByHashCode(current) + "->");
            pathLength++;
            path.remove(0);
            for(int i=0;i<numV;i++){
                if(edges[current][i] != 0 && !visited[i]){
                    visited[i] = true;
                    path.add(i);
                    if(i == v2Index){
                        System.out.println(linearProbingHash.findElementByHashCode(i));
                        path.add(i);
                        return ++pathLength;
                    }
                }
            }
        }

        return 0;
    }

    public int findSimplePathCount(String from, String to) {
        int fromIndex = linearProbingHash.findIndexOfElement(from);
        int toIndex = linearProbingHash.findIndexOfElement(to);
        boolean[] visited = new boolean[numV];
        ArrayList<Integer> neighbors = neighborsArray(fromIndex);
        int pathCount = 0;

        visited[fromIndex] = true;

        return findSimplePathCountRec(fromIndex, toIndex, visited, neighbors, pathCount);
    }

    private int findSimplePathCountRec(int fromIndex, int toIndex, boolean[] visited, ArrayList<Integer> neighbors, int pathCount) {

        for (int neighbor:neighbors) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                if (neighbor == toIndex) {
                    pathCount++;
                    return pathCount;
                }

                pathCount += findSimplePathCountRec(neighbor, toIndex, visited, neighborsArray(neighbor), pathCount);
            }
        }

        return pathCount;
    }

    public ArrayList<String> findNeighbors(String v1) {
        int v1Index = linearProbingHash.findIndexOfElement(v1);
        ArrayList<String> neighbors = new ArrayList<>();
        for (int i = 0; i < numV; i++) {
            if (edges[v1Index][i] != 0) {
                neighbors.add(linearProbingHash.findElementByHashCode(i));
            }
        }


        return neighbors;
    }

    private ArrayList<Integer> neighborsArray(int v1Index) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < numV; i++) {
            if (edges[v1Index][i] != 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public String findVerticesWithHighestDegree() {
        int maxDegree = 0;
        int maxDegreeIndex = 0;
        for (int i = 0; i < numV; i++) {
            int degree = findDegree(i);
            if (maxDegree < degree) {
                maxDegree = degree;
                maxDegreeIndex = i;
            }
        }

        return linearProbingHash.findElementByHashCode(maxDegreeIndex);
    }

    private int findDegree(int v1Index) {
        int degree = 0;
        for (int i = 0; i < numV; i++) {
            if (edges[v1Index][i] != 0) {
                degree += edges[v1Index][i];
            }
        }
        return degree;
    }

    public boolean isDirected() {
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                if (edges[i][j] != edges[j][i]) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isAdjacent(String v1, String v2) {
        int v1Index = linearProbingHash.findIndexOfElement(v1);
        int v2Index = linearProbingHash.findIndexOfElement(v2);
        return edges[v1Index][v2Index] != 0;
    }

    public boolean isThereACycle(String v) {
        int vIndex = linearProbingHash.findIndexOfElement(v);
        boolean[] visited = new boolean[numV];

        visited[vIndex] = true;

        return isThereACycleRec(vIndex, visited);
    }

    private boolean isThereACycleRec(int vIndex, boolean[] visited) {
        for (int i = 0; i < numV; i++) {
            if (edges[vIndex][i] != 0 && !visited[i]) {
                visited[i] = true;
                if (isThereACycleRec(i, visited)) {
                    return true;
                }
                visited[i] = false;
            } else if (edges[vIndex][i] != 0 && visited[i]) {
                return true;
            }
        }
        return false;
    }

    public int findTheVerticesCount(String vert) {
        int vIndex = linearProbingHash.findIndexOfElement(vert);
        boolean[] visited = new boolean[numV];
        int count = 0;

        for (int i = 0; i < numV; i++) {
            if (edges[vIndex][i] != 0) {
                count++;
            }
        }

        return count;
    }

}

