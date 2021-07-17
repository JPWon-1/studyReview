import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String changeCaseString = changeCase(a);
        System.out.println(changeCaseString);
    }
    
    public static String changeCase(String str){
        String answer = "";
        for(char x : str.toCharArray()){
            if(x>=65 && x<=90){
                answer += (char)(x+32);
            }else if(x>=97 && x<=122){
                answer += (char)(x-32);
            }
        }
        return answer;
    }
}
