package Chapter_7;

public class Average {
    public static int countGreaterThanPrevAverage(int[] arr) {
        int count = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                double avg = (double) sum / i;
                if (arr[i] > avg) {
                    count++;
                }
            }
            sum += arr[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] responseTimes = {100, 200, 150, 300};
        System.out.println(countGreaterThanPrevAverage(responseTimes));
        // Output: 2
    }
}

