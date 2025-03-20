package leetcode100;
import java.util.Scanner;
// accepted :)
public class LengthOfLastWord_58 {
    public static int lengthOfLastWord(String s){
        int count = 0; int i=s.length()-1;
        while(i>=0 && s.charAt(i) == ' '){
            i--;
        }
        while(i>=0 && s.charAt(i) != ' '){
            i--;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lengthOfLastWord(s));
    }
}
