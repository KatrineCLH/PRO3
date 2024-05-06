package Opgaver;

public class Opgave5 {
    public static void main(String[] args) {
        char[] kdb = {'G','R','S','G','G','R','S','R','G','G','R','S','G'};
        System.out.println(kdb);
        belgisk_flag(kdb);
        System.out.println(kdb);
    }


    public static void belgisk_flag(char[] belgisk_flag) {
        int low = 0;
        int high = belgisk_flag.length - 1;
        int mid = 0;

        while (mid <= high) {
            if (belgisk_flag[mid] == 'S') {
                char temp = belgisk_flag[low];
                belgisk_flag[low] = belgisk_flag[mid];
                belgisk_flag[mid] = temp;
                low++;
                mid++;
            } else if (belgisk_flag[mid] == 'G') {
                mid++;
            } else if (belgisk_flag[mid] == 'R') {
                char temp = belgisk_flag[mid];
                belgisk_flag[mid] = belgisk_flag[high];
                belgisk_flag[high] = temp;
                high--;
            }
        }
    }
}
