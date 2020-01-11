import java.io.File;
import java.util.ArrayList;
// import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Solution{
    HashMap<Integer,String> EmailInfo = new HashMap<>();
    HashMap<Integer,Integer> IDS = new HashMap<>();
    HashMap<Integer,Integer> log      = new HashMap<>();
    ArrayList<Integer>logStrings = new ArrayList<>();

    public void loadEmails(){
        
        File file = new File("emails.txt");

		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()){
                String temp = sc.nextLine();
                String [] temp1 =  temp.split(";",2);
                EmailInfo.put(Integer.parseInt(temp1[0]),temp1[1]);
                IDS.put(Integer.parseInt(temp1[0]), 0);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
        
    public void loadLog(){
        File file = new File("email-logs.txt");

		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()){
                String temp = sc.nextLine();
                String [] temp1 = temp.split(" ");
                log.put(Integer.parseInt(temp1[1].substring(0,1)),Integer.parseInt(temp1[3]));
                logStrings.add(Integer.parseInt(temp1[3]));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
        Solution obj = new Solution();
        // HashMap<Integer,Integer> log1     = new HashMap<>();
        obj.loadEmails();
        obj.loadLog();
        System.out.println(obj.EmailInfo.get(0));
        System.out.println(obj.log.get(1));
        System.out.println(obj.logStrings.get(1));


        for (int i : obj.logStrings) {
            try{
                int j = obj.log.get(i);
                obj.IDS.put(i, j+1);
            }catch(Exception e){

            }
           
        }



        System.out.println(obj.IDS.get(1));

    //     ArrayList<Integer> id = new ArrayList<>();
    //     File file = new File("emails.txt");

	// 	try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            
	// 		while (sc.hasNextLine()){
    //             String temp = sc.nextLine();
    //             String [] temp1 =  temp.split(";",2);
    //             id.add(Integer.parseInt(temp1[0]));
	// 		}
	// 	}
	// 	catch (IOException e) {
	// 		e.printStackTrace();
    //     }
    //     int max = 0; 
    //    for (int i : id) {
    //        try {
    //         if(max < obj.log.get(i)) {
    //             max = obj.log.get(i);
    //         }
    //        } catch (Exception e) {
    //            //TODO: handle exception
    //        }
           
    //    }
    //    System.out.println("MAX : "+max);

        
    }
}