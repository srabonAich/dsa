package arrayList;
import java.util.ArrayList;
import java.util.Collections;

public class CreateArraylist {
    //how to swap two numbers//how to pass an Arraylist in a function
    public static void swap(ArrayList<Integer>list, int idx1, int idx2){
        int temp = list.get(idx1);
        list.set(idx1,list.get(idx2));
        list.set(idx2, temp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<Boolean> list3 = new ArrayList<>();

        list.add(1);// adding takes O(1)
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);

        list.add(1, 20); //this adding takes O(n)
        System.out.println(list);

        int x = list.get(2);//getting takes O(1)
        System.out.println(x);

        list.remove(2);//removing element takes O(n)
        System.out.println(list);

        list.set(2, 10); //setting element takes O(n)
        System.out.println(list);

        System.out.println(list.contains(1));//takes O(n)
        System.out.println(list.contains(14));

        System.out.println(list.size()); //the total length of the list

        System.out.println(list);
        swap(list,2,3);
        System.out.println(list);

        Collections.sort(list); // default sorting // ascending
        System.out.println(list);

        Collections.sort(list, Collections.reverseOrder()); //descending order
        System.out.println(list);
    }
}
