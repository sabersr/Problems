import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class LeetCode {

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5,5,10,20,5,5,5,5,5,5,5,5,5,10,5,5,20,5,20,5}));
    }
    
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] maxRow = new int[grid.length];
        int[] maxColumn = new int[grid[grid.length-1].length];
        for (int i=0;i<grid.length;i++) {
            maxRow[i] = grid[i][0];
            for (int j=1;j<grid[i].length;j++) {
                if (maxRow[i]<grid[i][j]) maxRow[i]=grid[i][j];
            }
        }
        for (int i=0;i<grid[grid.length-1].length;i++) {
            maxColumn[i] = grid[0][i];
            for (int j=1;j<grid.length;j++) {
                if (maxColumn[i]<grid[j][i]) maxColumn[i]=grid[j][i];
            }
        }
        int sum = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++) {
                int maxIncrease = maxRow[i];
                if (maxIncrease>maxColumn[j]) maxIncrease = maxColumn[j];
                sum = sum + maxIncrease-grid[i][j];
            }
        }
        return sum;
    }
    
    public static String reverseString(String s) {
        for(int i=0;i<s.length()/2;i++) {
            char temp1 = s.charAt(i);
            char temp2 = s.charAt(s.length()-1-i);
            String p1 = s.substring(0,i);
            String p2 = s.substring(i+1,s.length()-1-i);
            String p3 = s.substring(s.length()-i,s.length());
            s = p1+temp2+p2+temp1+p3;
        }
        return s;
    }
    
    public int[] shortestToChar(String S, char C) {
        char[] chars = S.toCharArray();
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for (int i=0; i<chars.length;i++) {
            if (chars[i] == C) pos.add(i);
        }
        int[] sDistance = new int[chars.length];
        for (int i=0;i<chars.length;i++) {
            sDistance[i]= Math.abs(i-pos.get(0));
            for (int j=1;j<pos.size();j++) {
                if (sDistance[i] > Math.abs(i-pos.get(j))) {
                    sDistance[i] =  Math.abs(i-pos.get(j));
                }
            }
        }
        return sDistance;
    }
    
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dup = new ArrayList<Integer>();
        HashSet<Integer> hSet = new HashSet<Integer>();
        for (int i=0;i<nums.length;i++) {
            int size = hSet.size();
            hSet.add(nums[i]);
            if (hSet.size() != size) dup.add(nums[i]);
        }
        return dup;
    }
    
    public static void emaildistribution() {
        Scanner scnr = new Scanner(System.in);
        int m = scnr.nextInt();
        int n = scnr.nextInt();
        int[] email = new int[m];
        for (int i=0;i<n;i++) {
            email[scnr.nextInt()-1]++;
        }
        for (int i=0;i<m;i++) {
            System.out.print(email[i]+" ");
        }
    }
    
    public static long balanceNumber() {
        Scanner scnr = new Scanner(System.in);
        String numbers = scnr.next();
        char[] number = numbers.toCharArray();
        long difference = Long.MAX_VALUE;
        for (int i=0;i<number.length;i++) {
            long left = 0;
            for (int j=0;j<i;j++) {
                left = left + Character.getNumericValue(number[j]) * (i-j);
            }
            long right = 0;
            for (int j=i;j<number.length;j++) {
                right = right + Character.getNumericValue(number[j]) * (j-i);
            }
            if (difference>Math.abs(left-right)) difference = Math.abs(left-right);
        }
        return difference;
    }
    
    public static String[] uncommonFromSentences(String A, String B) {
        String[] ufs = new String[A.length()+B.length()];
        String[] a = A.split(" ");
        //boolean[] ab = new boolean[a.length];
        String[] b = B.split(" ");
        //boolean[] bb = new boolean[b.length];
        HashSet<String> u = new HashSet<String>();
        int index = 0;
        for (int i=0;i<a.length;i++) {
            int size = u.size();
            u.add(a[i]);
            if (size == u.size()) {
                ufs[index]=a[i];
                index++;
            }
        }
        for (int i=0;i<b.length;i++) {
            int size = u.size();
            u.add(b[i]);
            if (size == u.size()) {
                ufs[index]=b[i];
                index++;
            };
        }
        for (int i=0;i<index;i++) {
            u.remove(ufs[i]);
        }
        return u.toArray(new String[u.size()]);
    }
    
    public static int peakIndexInMountainArray(int[] A) {
        int begin = 0;
        int end = A.length-1;
        int middle = (begin+end)/2;
        while ((middle<end) && (middle>begin)) {
            if ((A[middle-1]<A[middle]) && (A[middle]<A[middle+1])) {
                begin = middle;
            } else if ((A[middle-1]>A[middle]) && (A[middle]>A[middle+1])) {
                end = middle;
            }
            if ((A[begin]<A[middle]) && (A[middle]>A[end])) {
                return middle;
            }
            middle = (begin+end)/2;
        }
        return middle;
    }
    
    public static boolean lemonadeChange(int[] bills) {
        boolean flag = true;
        int[] money = new int[3];
        int index = 0;
        while ((flag) && (index<bills.length)){
            if (bills[index] == 5) money[0]++;
            if (bills[index] == 10) {
                money[1]++;
                money[0]--;
                if (money[0]<0) flag = false;
            }
            if (bills[index] == 20) {
                money[2]++;
                if (money[1]<=0) {
                    money[0] = money[0] - 3;
                    if (money[0]<0) flag = false;
                } else {
                    money[0]--;
                    money[1]--;
                    if ((money[0]<0) || (money[1]<0)) flag = false;
                }
            }
            index++;
        }
        return flag;
    }
}
