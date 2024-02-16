import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Payment Class reads in a file of names and orders
 * It determines who should pay based on cost and who payed
 * last
 *  @author  Henry Gates
 *  @version 1.0 
 * 
 */
public class Payment {

    public static void main(String[] args){

        /*
         * 2 possible Arguments - (Required) list of orders from text files which lines follow
         * order of "<NAME>, <ORDER>: $<COST>",  (Optional) Name of last person to pay
         * System exists and errors if wrong number of arguments exist
         */
        if (args.length != 1 && args.length != 2) {
            System.err.println("Error: Need 1 to 2 arguments");
            System.exit(1);
        }
        String ordersFile = args[0];
        String lastPayed = (args.length > 1) ? args[1] : null;
        try{
            /*
             * Creates Map of names and order costs
             * Assumes that txt file lines follow order of <NAME>, <ORDER>: $<COST>
             */
            HashMap<String, Double> orders = getOrders(ordersFile);

            /*
             * returns formatted line of who will pay based
             * on cost of order and who payed last  
             */
            System.out.println(String.format("%s should pay!", getMax(orders, lastPayed)));
        }
        catch (IOException e){
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
    /**
     * @param fileName - file for reader to go through and pull from
     * @return HashMap<String, Double> of names and order costs
     */
    public static HashMap<String, Double> getOrders(String fileName) throws IOException{
        HashMap<String, Double> orders = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine())!= null){
                if (!line.trim().isEmpty()){
                    orders.put(line.substring(0,line.indexOf(",")),
                     Double.parseDouble(line.substring(line.indexOf("$")+1 )) );
                }
                else{
                    throw new IOException("Blank line found in file: " + fileName);
                }
            }
        }
        return orders;
    }

    /**
     * 
     * @param orders - Hashmap of Names of people and there orders
     * @param lastPayed - The last person to have payed, will be excluded from paying
     * @return The persons name who has the highest cost of order
     */
    public static String getMax(HashMap<String, Double> orders, String lastPayed) {
            if (lastPayed != null && orders.containsKey(lastPayed)) {
                orders.remove(lastPayed);
            }
            if (orders.isEmpty()){
                return "no one";
            }

        return orders.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}