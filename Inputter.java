import java.util.Scanner;

public class Inputter {
    public static String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        while (true) {   
            System.out.print(msg);
            String input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println("Can not blank!");
            }else{
                return input;
            }
        }
    }
    
    public static int getInteger(String msg) {
        Scanner sc = new Scanner(System.in);
        while (true) {            
            System.out.print(msg);
            try {
                String input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println("Can not blank");
            }else{
                int data = Integer.parseInt(input);
                if(data > 0){
                    return data;
                }else{
                    System.out.println("Avaibility must be greater than 0!");
                }
            }
            } catch (Exception e) {
                System.out.println("Please enter integer number");
            }
        }
    } 

    public static double getDouble(String msg) {
        Scanner sc = new Scanner(System.in);
        while (true) {            
            System.out.print(msg);
            try {
                String input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println("Can not blank");
            }else{
                double data = Double.parseDouble(input);
                if(data > 0){
                    return data;
                }else{
                    System.out.println("Avaibility must be greater than 0!");
                }
            }
            } catch (Exception e) {
                System.out.println("Please enter double number");
            }
        }
    } 
}
