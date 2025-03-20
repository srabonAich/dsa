package leetcode100;
// Accepted :)
import java.util.*;
public class ValidParantheses_20 {
    public static boolean isValid(String s){
        Stack<Character> st = new Stack<>();
        for(int i=0; i<s.length(); i++){
            if((s.charAt(i)=='(')||(s.charAt(i)=='{')||(s.charAt(i)=='[')){
                st.push(s.charAt(i));
            }else if(s.charAt(i) == ')'){
                if(st.isEmpty()) return false;
                if(st.peek() == '('){
                    st.pop();
                }else return false;
            }else if(s.charAt(i) == '}'){
                if(st.isEmpty()) return false;
                if(st.peek() == '{'){
                    st.pop();
                }else return false;
            }else if(s.charAt(i) == ']'){
                if(st.isEmpty()) return false;
                if(st.peek() == '['){
                    st.pop();
                }else return false;
            }
        }
        if(st.isEmpty())
            return true;

        return false;
    }

    public static void main(String[] args) {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        System.out.println(isValid(s));
    }
}
