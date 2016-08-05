package Main;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
	// write your code here

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


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Integer temp =  Integer.parseInt(reader.readLine());
            if(temp==0) throw new NullPointerException("eUNDEFINED");

            String inputhex = Integer.toHexString(temp);

            System.out.println("HEX cod = 0x" + inputhex);
            System.out.println("");

            if(inputhex.length()>=3){

                if(ngcccall.containsKey(inputhex.substring(inputhex.length()-3, inputhex.length())))
                    System.out.println("NGCC call = " + ngcccall.get(inputhex.substring(inputhex.length()-3, inputhex.length())));
                else throw new NullPointerException("Не корректный код!");

                if(inputhex.length()>=4){

                    if(dnoutcall.containsKey(inputhex.substring(inputhex.length()-4, inputhex.length()-3)))
                        System.out.println("DN Out call = " + dnoutcall.get(inputhex.substring(inputhex.length()-4, inputhex.length()-3)));
                    else throw new NullPointerException("Не корректный код!");

                    if(inputhex.length()>=5){

                        if(dnincall.containsKey(inputhex.substring(inputhex.length()-5, inputhex.length()-4)))
                            System.out.println("DN In call = " + dnincall.get(inputhex.substring(inputhex.length()-5, inputhex.length()-4)));
                        else throw new NullPointerException("Не корректный код!");

                        if(inputhex.length()>=6){

                            if(nacdcall.containsKey(inputhex.substring(inputhex.length()-6, inputhex.length()-5)))
                                System.out.println("NACD call = " + nacdcall.get(inputhex.substring(inputhex.length()-6, inputhex.length()-5)));
                            else throw new NullPointerException("Не корректный код!");

                            if(inputhex.length()>=7){

                                if(acdcall.containsKey(inputhex.substring(inputhex.length()-7, inputhex.length()-6)))
                                    System.out.println("ACD call = " + acdcall.get(inputhex.substring(inputhex.length()-7, inputhex.length()-6)));
                                else throw new NullPointerException("Не корректный код!");

                                if(inputhex.length()>=8){

                                    if(inputhex.substring(inputhex.length()-8, inputhex.length()-7).equals("1"))
                                        System.out.println("Independent call Independent call (i.e. Walkaway)");



                                }

                            }

                        }

                    }

                }
            }







        }
        catch (Exception x){
            System.out.println(x.getMessage());
        }



    }
}
