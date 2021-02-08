package Tests;

public class DivisibleNumbers {

    public static void main(String[]args ){

        System.out.println("");

        for(int  i = 100; i>=1;i--){
            if(i%5 == 0 && i%3 == 0){
                System.out.println(i + " - " + "Testing");
            } else if(i%3 == 0){
                System.out.println(i + " - " + "Software");
            } else if( i%5 == 0){
                System.out.println(i + " - " + "Agile");
            }else {
                System.out.println(i);
            }
        }

    }

}
