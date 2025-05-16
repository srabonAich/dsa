package arrayList;
import java.sql.Array;
import java.util.ArrayList;
public class Create2DAL {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1); list1.add(2); list1.add(3); list1.add(4);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2); list2.add(4); list2.add(6);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(3); list3.add(6);

        mainList.add(list1);
        mainList.add(list2);
        mainList.add(list3);

        System.out.println(mainList);

        for(int i=0; i<mainList.size(); i++){
            ArrayList<Integer>currlist = mainList.get(i);
            for(int j=0; j<currlist.size(); j++)
                System.out.print(currlist.get(j) + " ");
            System.out.println();
        }
    }
}
