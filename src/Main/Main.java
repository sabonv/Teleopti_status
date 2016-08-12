package Main;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here

        System.out.println("Enter the code (a blank line - input completion):");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Cod> allcod = new ArrayList<Cod>();

//Ввод кодов, по пустой строке конец
        while (true) {

            String tempread = reader.readLine();
            if (tempread.equals("")) break;
            allcod.add(new Cod(tempread));

        }

// Обработка и вывод результата
        for (Cod z: allcod) {
            if (!z.wrongcod) {
                parscod(z);
                System.out.println(z);
                System.out.println("");
            }

        }

// Запись в файл
        PrintWriter out = new PrintWriter(new FileWriter("Results.txt"));
        try {
            for (Cod z: allcod) {
                if (!z.wrongcod) {
                    out.println(z);
                    out.println("");
                }
            }
        }
        catch (Exception x) {
            System.out.println("Error writing to file:" + x.getMessage());
        }
        finally {
            if(out != null) out.close();
        }



    }
    public static void parscod(Cod tempcod) {

//получаем код
        Long temp =  tempcod.longcod;

//Провера
        if(temp==0) tempcod.eUNDEFINED = true;
        if(temp==4294967295l) tempcod.NGCC_LOGOUT = true;
        tempcod.wrongcod = (temp > 4294967295l || (((temp%16) != 0))&&(temp!=4294967295l));

//Перевод в HEX
        String inputhex = Long.toHexString(temp);

//Дописываем до 8 знаков
        while (inputhex.length()<8){
            inputhex = "0"+inputhex;
        }

//Заполняем проверочные массивы МАП
            Map<String, String> acdcall = new HashMap<>();
            acdcall.put("1", "eACD_ONHOLD");
            acdcall.put("2", "eACD_ACTIVE");
            acdcall.put("4", "eACD_RESERVE_1");
            acdcall.put("8", "eACD_RESERVE_2");
            acdcall.put("0", "Undefined");

            Map<String, String> nacdcall = new HashMap<>();
            nacdcall.put("1", "eNACD_ONHOLD");
            nacdcall.put("2", "eNACD_ACTIVE");
            nacdcall.put("4", "eNACD_RESERVE_1");
            nacdcall.put("8", "eNACD_RESERVE_2");
            nacdcall.put("0", "Undefined");

            Map<String, String> dnincall = new HashMap<>();
            dnincall.put("1", "eDN_IN_ONHOLD");
            dnincall.put("2", "eDN_IN_ACTIVE");
            dnincall.put("3", "eDN_IN_ACTIVE_ONHOLD");
            dnincall.put("4", "eDN_IN_RESERVE_1");
            dnincall.put("8", "eDN_IN_RESERVE_2");
            dnincall.put("0", "Undefined");

            Map<String, String> dnoutcall = new HashMap<>();
            dnoutcall.put("1", "eDN_OUT_ONHOLD");
            dnoutcall.put("2", "eDN_OUT_ACTIVE");
            dnoutcall.put("3", "eDN_OUT_ACTIVE_ONHOLD");
            dnoutcall.put("4", "eDN_OUT_RESERVE_1");
            dnoutcall.put("8", "eDN_OUT_RESERVE_2");
            dnoutcall.put("0", "Undefined");

            Map<String, String> ngcccall = new HashMap<>();
            ngcccall.put("100", "eNGCC_ACTIVE");
            ngcccall.put("080", "eNGCC_ONHOLD");
            ngcccall.put("040", "eNGCC_NOTRDY");
            ngcccall.put("020", "eNGCC_BRK");
            ngcccall.put("010", "eNGCC_IDLE");
            ngcccall.put("008", "eNGCC_RESERVE");
            ngcccall.put("004", "eNGCC_CALL_PRESENT");
            ngcccall.put("002", "eNGCC_CONSULTATION");
            ngcccall.put("001", "eNGCC_EMERGENCY");
            ngcccall.put("200", "eNGCC_BUSY");
            ngcccall.put("400", "eNGCC_RESERVE_1");
            ngcccall.put("800", "eNGCC_RESERVE_2");
            ngcccall.put("000", "Undefined");

//Разбираем код
        try {
            if(!tempcod.wrongcod && !tempcod.eUNDEFINED && !tempcod.NGCC_LOGOUT) {
                if (ngcccall.containsKey(inputhex.substring(inputhex.length() - 3, inputhex.length())))
                    tempcod.codmap.put("NGCC call", ngcccall.get(inputhex.substring(inputhex.length() - 3, inputhex.length())));
                else tempcod.wrongcod = true;

                if (dnoutcall.containsKey(inputhex.substring(inputhex.length() - 4, inputhex.length() - 3)))
                    tempcod.codmap.put("DN Out call", dnoutcall.get(inputhex.substring(inputhex.length() - 4, inputhex.length() - 3)));
                else tempcod.wrongcod = true;

                if (dnincall.containsKey(inputhex.substring(inputhex.length() - 5, inputhex.length() - 4)))
                    tempcod.codmap.put("DN In call", dnincall.get(inputhex.substring(inputhex.length() - 5, inputhex.length() - 4)));
                else tempcod.wrongcod = true;

                if (nacdcall.containsKey(inputhex.substring(inputhex.length() - 6, inputhex.length() - 5)))
                    tempcod.codmap.put("NACD call", nacdcall.get(inputhex.substring(inputhex.length() - 6, inputhex.length() - 5)));
                else tempcod.wrongcod = true;

                if (acdcall.containsKey(inputhex.substring(inputhex.length() - 7, inputhex.length() - 6)))
                    tempcod.codmap.put("ACD call", acdcall.get(inputhex.substring(inputhex.length() - 7, inputhex.length() - 6)));
                else tempcod.wrongcod = true;

                if (inputhex.substring(inputhex.length() - 8, inputhex.length() - 7).equals("1"))
                    tempcod.walkaway = true;
            }

        }


        catch (Exception x){
            System.out.println(x.getMessage());
        }




    }

}
