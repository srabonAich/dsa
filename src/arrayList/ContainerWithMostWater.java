package arrayList;
/*
Container with most water
for given 'n' lines on x-axis, use 2 lines to form a container such
that it holds maximum water.
height = {1,8,6,2,5,4,8,3,7}
output = 7*7 = 49

optimized approach = two pointer approach -- O(n)
 */

import java.util.ArrayList;

public class ContainerWithMostWater {
    public static int bruteForce(ArrayList<Integer>height){
        int maxWater = 0;
        for(int i = 0; i<height.size(); i++){
            for(int j=i+1; j<height.size(); j++){
                int ht = Math.min(height.get(i), height.get(j));
                int wi = j - i;
                int currWater = ht * wi;
                maxWater = Math.max(maxWater, currWater);
            }
        }
        return maxWater;
    }

    public static int storeWater(ArrayList<Integer>height){
        int maxWater=0;
        int lp = 0;
        int rp = height.size()-1;
        while(lp<rp){
            int ht=Math.min(height.get(lp),height.get(rp));
            int wi=rp-lp;
            int currWater = ht*wi;
            maxWater = Math.max(maxWater, currWater);
            if(height.get(lp) < height.get(rp)) lp++;
            else rp--;
        }
        return maxWater;
    }
    public static void main(String[] args) {
        ArrayList<Integer> height = new ArrayList<>();
        height.add(1);
        height.add(8);
        height.add(6);
        height.add(2);
        height.add(5);
        height.add(4);
        height.add(8);
        height.add(3);
        height.add(7);
        //System.out.println(bruteForce(height));
        System.out.println(storeWater(height));
    }
}
