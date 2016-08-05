package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sabo on 05/08/16.
 */
public class Cod {

    String cods;
    Map<String, String> codmap = new HashMap<>();


    Cod(String cods){
        try {
            this.cods = cods;
            this.codmap.put("NGCC call", "");
            this.codmap.put("DN Out call", "");
            this.codmap.put("DN In call", "");
            this.codmap.put("NACD call", "");
            this.codmap.put("ACD call", "");
            this.codmap.put("Walkaway", "");

        }
        catch (Exception e){
            System.out.println("конструктор Cod: " + e.getMessage());
        }

    }

}
