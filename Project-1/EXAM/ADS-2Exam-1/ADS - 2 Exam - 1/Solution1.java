import java.io.File;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Collections;
// import java.util.Comparator;
import java.util.HashMap;
// import java.util.List;
import java.util.Scanner;

public class Solution1 {
    HashMap<Integer, String> map1 = new HashMap<>();
    // ArrayList<Integer> to = new ArrayList<>();
    HashMap<Integer, ArrayList<String>> map2 = new HashMap<>();
    ArrayList<Integer> id = new ArrayList<>();

    public void loadEmailData() {

        try {
            File file = new File("emails.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine() == true) {
                String[] temp = in.nextLine().split(";");
                map1.put(Integer.parseInt(temp[0]), temp[1]);
                id.add(Integer.parseInt(temp[0]));

            }
        } catch (Exception e) {
            // TODO: handle exception

        }
    }

    public void loadLogData() {
        int c = 0;
        try {
            File file = new File("email-logs.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine() == true) {
                String[] temp = in.nextLine().split(" ");
                ArrayList<String> from = new ArrayList<>();
                from.add(temp[1].substring(0, 1) + ":" + temp[3]);
                c = c + 1;
                map2.put(c, from);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void main(String[] args) {
        Solution1 obj = new Solution1();
        obj.loadEmailData();
        obj.loadLogData();
        Digraph di = new Digraph(obj.map2.size());
        System.out.println(obj.map2.size());
        // System.out.println(obj.map2);

        for (int i = 0; i < obj.map2.size(); i++) {
            try {
                for (String s : obj.map2.get(i)) {
                    String temp[] = s.split(":");
                    int v = Integer.parseInt(temp[0]);
                    int w = Integer.parseInt(temp[1]);
                    // System.out.println(v+" : "+w);
                    di.addEdge(v, w);

                }

            } catch (Exception e) {
                // TODO: handle exception
            }

        }
        System.out.println("Vertices : " + di.vertices + " Edges : " + di.edges + " indegree :" + di.indegree(1));
        ArrayList<T> l = new ArrayList<>();
        for (int i = 0; i < 265214; i++) {
            l.add(new T(i, di.indegree(i)));
        }
        // Comparator<T> compareByIndegree =(store o1,store o2) -> o1.indegree.compareTo(o2.indegree);
        Collections.sort(l);
        for (int i = 0; i <9; i++) {
            System.out.println(l.get(i).id +" : "+obj.map1.get((l.get(i).id))+" : "+l.get(i).indegree);
            
        }        
    }
}

