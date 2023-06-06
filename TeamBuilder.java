// Name:   
// Date:
 
import java.util.*;
import java.io.*;

public class TeamBuilder
{
   public static void main(String[] args) 
   {
      String[] path = {"010", "000", "110"};	   
      //String[] path = {"0010", "1000", "1100", "1000"};
      //String[] path = {"01000", "00100", "00010", "00001", "10000"};
      //String[] path = {"0110000", "1000100", "0000001", "0010000", "0110000", "1000010", "0001000"};
      
      int[] ret = specialLocations(path);
      System.out.println("{" + ret[0] + ", " + ret[1] + "}");
   }

   public static int[] specialLocations(String[] paths)
   {
	   int n = paths.length;
       int[][] graph = new int[n][n];

       // Build the graph from the paths
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               graph[i][j] = paths[i].charAt(j) - '0';
           }
       }

       // Use Floyd-Warshall algorithm to find the transitive closure of the graph
       for (int k = 0; k < n; k++) {
           for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                   if (graph[i][k] == 1 && graph[k][j] == 1) {
                       graph[i][j] = 1;
                   }
               }
           }
       }

       int locationsReachAll = 0;
       int locationsReachedByAll = 0;

       // Count the number of locations that can reach all other locations
       for (int i = 0; i < n; i++) {
           boolean canReachAll = true;
           for (int j = 0; j < n; j++) {
               if (i != j && graph[i][j] == 0) {
                   canReachAll = false;
                   break;
               }
           }
           if (canReachAll) {
               locationsReachAll++;
           }
       }

       // Count the number of locations that are reachable by all other locations
       for (int j = 0; j < n; j++) {
           boolean reachedByAll = true;
           for (int i = 0; i < n; i++) {
               if (i != j && graph[i][j] == 0) {
                   reachedByAll = false;
                   break;
               }
           }
           if (reachedByAll) {
               locationsReachedByAll++;
           }
       }

       return new int[]{locationsReachAll, locationsReachedByAll};
      
   }

}