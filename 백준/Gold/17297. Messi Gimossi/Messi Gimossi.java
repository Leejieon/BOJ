import java.util.Scanner;

public class Main {
    public static long[] messi = new long[1000];
    public static int flag = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        messi[1] = 5;
        messi[2] = 13;

        int index = 3;
        while(true){
            messi[index] = messi[index-1]+1+messi[index-2];
            if(messi[index]>N){
                break;
            }
            index++;
        }

        find(index, N);

    }

    public static void find(int index, long n){
        if(flag==1){
            System.out.println("Messi Messi Gimossi");
            return;
        }
        if(index==1){
            System.out.println("Messi".charAt((int)n-1));
            return;
        }
        else if(index==2){
            if(n==6){
                System.out.println("Messi Messi Gimossi");
            }
            else
                System.out.println("Messi Gimossi".charAt((int)n-1));
            return;
        }

        if(messi[index-1]>=n){
            find(index-1, n);
        }
        else if(messi[index-1]+1==n){
            flag=1;
            find(index-1, n);
        }
        else{
            find(index-2, n-messi[index-1]-1);
        }

    }


}
