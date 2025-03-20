package leetcode100;
// accepted :)
public class FirstOccurence_28 {
    public static int firstOccurence(String haystack, String needle){
        int j; int i = 0;
        if(haystack.length() < needle.length()) return -1;
        while(i<haystack.length()){
            for(j=0; j<needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if(j == needle.length()) return i;
            i = i+1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstOccurence("leetcode", "leeto"));

    }
}
