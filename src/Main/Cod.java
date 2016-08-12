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
    Long longcod;
    boolean NGCC_LOGOUT = false, eUNDEFINED = false, wrongcod = false, walkaway = false;
    Map<String, String> codmap = new HashMap<>();


    Cod(String cods) throws Exception{
        try {
            this.longcod = Long.parseLong(cods);
            this.cods = cods;
            this.codmap.put("NGCC call", "");
            this.codmap.put("DN Out call", "");
            this.codmap.put("DN In call", "");
            this.codmap.put("NACD call", "");
            this.codmap.put("ACD call", "");

        }
        catch (Exception e){
            System.out.println("Input Error, , make sure that you enter the correct code: " + e.getMessage());
            this.wrongcod = true;

        }

    }

    @Override
    public String toString(){
        String oneline = "";
// Вывод ошибки кода
        if(this.wrongcod == true) return "Cod DEC " + this.cods + "\n" + "Invalid code!";

// Вывод кода DEC
        oneline = oneline + "Cod DEC " + this.cods + "\n";

// Вывод кода HEX
        String inputhex = Long.toHexString(this.longcod);
        while (inputhex.length()<8){
            inputhex = "0"+inputhex;
        }
        oneline = oneline + "Cod HEX 0x" + inputhex + "\n" + "\n";

// Логоут и неопределён
        if (NGCC_LOGOUT==true) return (oneline + "NGCC_LOGOUT");
        if (eUNDEFINED==true) return (oneline + "eUNDEFINED");

// Значения

        oneline = oneline + ("NGCC call - " + this.codmap.get("NGCC call")) + "\n";
        oneline = oneline + ("DN Out call - " + this.codmap.get("DN Out call")) + "\n";
        oneline = oneline + ("DN In call - " + this.codmap.get("DN In call")) + "\n";
        oneline = oneline + ("NACD call - " + this.codmap.get("NACD call")) + "\n";
        oneline = oneline + ("ACD call - " + this.codmap.get("ACD call")) + "\n";
        if(this.walkaway == true) oneline = oneline + ("eNGCC_WALKAWAY") + "\n";

        oneline = oneline+ "\n";

// Одной строкой

        if(!this.codmap.get("NGCC call").equals("Undefined")) oneline = oneline + this.codmap.get("NGCC call");
        if(!this.codmap.get("DN Out call").equals("Undefined")) oneline = oneline + "_" + this.codmap.get("DN Out call");
        if(!this.codmap.get("DN In call").equals("Undefined")) oneline = oneline + "_" + this.codmap.get("DN In call");
        if(!this.codmap.get("NACD call").equals("Undefined")) oneline = oneline + "_" + this.codmap.get("NACD call");
        if(!this.codmap.get("ACD call").equals("Undefined")) oneline = oneline + "_" + this.codmap.get("ACD call");
        if(this.walkaway == true) oneline = oneline + "_" + "eNGCC_WALKAWAY";

        return oneline;
    }

}
