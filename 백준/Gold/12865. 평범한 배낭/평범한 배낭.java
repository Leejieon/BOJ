import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(0, 0));
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            items.add(new Item(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(items);


        for (int item = 1; item <=N ; item++) {
            for (int weight = 1; weight <= K; weight++) {
                if (weight < items.get(item).weight) {
                    dp[item][weight] = dp[item - 1][weight];
                }
                else{
                    dp[item][weight] = Math.max(dp[item-1][weight], dp[item-1][weight-items.get(item).weight] + items.get(item).value);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

    static class Item implements Comparable<Item>{
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            // 무게를 기준으로 오름차순 정렬
            return this.weight - o.weight;
        }
    }
}