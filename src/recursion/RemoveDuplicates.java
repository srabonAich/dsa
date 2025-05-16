package recursion;
/*
remove all the duplicate characters from a string
the string will contain only 'a' to 'z'
 */
public class RemoveDuplicates {
    public static void removeDuplicates(String str, int idx, StringBuilder newString, boolean map[]){
        if(idx == str.length()){
            System.out.println(newString);
            return;
        }
        char currChar = str.charAt(idx);
        if(map[currChar-'a'])
            removeDuplicates(str, idx+1,newString, map);
        else{
            map[currChar - 'a'] = true;
            removeDuplicates(str, idx+1, newString.append(currChar), map);
        }
    }

    public static void main(String[] args) {
        String str = "aanddkfldff";
        removeDuplicates(str, 0, new StringBuilder(""),new boolean[26]);
    }
}
