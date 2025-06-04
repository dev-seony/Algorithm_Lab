// 22210596 전선영

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HW1 {
    private Scanner scanner = new Scanner(System.in);
    File file = null;
    double myX, myY = 0;
    static int k = 0;
    static int n = 0;
    static double[] X;
    static double[] Y;
    static Comparable[] distance, distanceAdv;

    private void link(){
        System.out.print("파일 이름 >> ");
        String filename = scanner.next();
        try{
            file = new File(filename);
            Scanner fscanner = new Scanner(new FileReader(file));
            myX = fscanner.nextDouble();
            myY = fscanner.nextDouble();

            k = fscanner.nextInt();
            n = fscanner.nextInt();

            X = new double[n];
            Y = new double[n];
            distance = new Comparable[n];
            distanceAdv = new Comparable[n];

            int i = 0;

            if (k == -1){
                while(fscanner.hasNext()) {
                    X[i] = fscanner.nextDouble();
                    Y[i++] = fscanner.nextDouble();
                }
            } else {
                for (i = 0; i < n; i++){
                    X[i] = fscanner.nextDouble();
                    Y[i] = fscanner.nextDouble();
                }
            }
            fscanner.close();
        } catch(IOException e) {
            System.out.println(filename + "을 열 수 없음");
        }
    }

    private void calDistance(){
        double number = 0.0;
        for (int i = 0; i < n; i++){
            number = Math.sqrt(Math.pow(X[i] - myX, 2) + Math.pow(Y[i] - myY, 2));
            distance[i] = distanceAdv[i] = number;
        }
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;

        double tmpX = X[i]; X[i] = X[j]; X[j] = tmpX;
        double tmpY = Y[i]; Y[i] = Y[j]; Y[j] = tmpY;
    }

    private static void show(Comparable[] a){
        int repeatNum = (k == -1) ? n : k;
        for (int i = 0; i < repeatNum; i++) {
            System.out.printf("%d: %.6f, %.6f\t\t거리: %.6f\n", i, X[i], Y[i], (Double)a[i]);
            if (i >= 30) {
                System.out.println("...");
                break;
            }
        }
        System.out.println();
    }

    private static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    private static void SelectionSort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
        assert isSorted(a);
    }

    private static void K_SelectionSort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < k; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    private static void InsertionSort(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--){
                exch(a, j, j-1);
            }
        }
        assert isSorted(a);
    }

    private static void K_InsertionSort(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--){
                exch(a, j, j-1);
            }
            if (i >= k) break;
        }
    }

    private static void ShellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3 * h + 1;

        while (h >= 1){
            for (int i = 0; i < N; i++){
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h){
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
        assert isSorted(a);
    }

    private static void K_ShellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1){
            for (int i = 0; i < N; i++){
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h){
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    private static void mergeTD(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            if (i > mid) {
                a[k] = aux[j];
                X[k] = X[j];
                Y[k] = Y[j];
                j++;
            } else if (j > hi) {
                a[k] = aux[i];
                X[k] = X[i];
                Y[k] = Y[i];
                i++;
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j];
                X[k] = X[j];
                Y[k] = Y[j];
                j++;
            } else {
                a[k] = aux[i];
                X[k] = X[i];
                Y[k] = Y[i];
                i++;
            }
        }
    }

    private static void TDmergeSort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        TDmergeSort(a, aux, 0, a.length - 1);
    }

    private static void TDmergeSort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo) return;
        if (hi <= lo + 6){
            Comparable[] b = new Comparable[hi - lo + 1];
            System.arraycopy(a, lo, b, 0, hi - lo + 1);
            InsertionSort(b);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        TDmergeSort(a, aux, lo, mid);
        TDmergeSort(a, aux, mid + 1, hi);
        if (!less(aux[mid + 1], aux[mid])) return;
        mergeTD(a, aux, lo, mid, hi);
    }

    private static void K_TDmergeSort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        K_TDmergeSort(a, aux, 0, a.length - 1);
    }

    private static void K_TDmergeSort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo) return;
        if (hi <= lo + 6){
            for (int i = lo + 1; i <= hi; i++){
                for (int j = i; j > lo && less(a[j], a[j - 1]); j--){
                    exch(a, j, j - 1);
                }
            }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        K_TDmergeSort(a, aux, lo, mid);
        K_TDmergeSort(a, aux, mid + 1, hi);
        mergeTD(a, aux, lo, mid, hi);
    }

    private static void mergeBU(Comparable[] in, Comparable[] out, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            if (i > mid) {
                out[k] = in[j];
                X[k] = X[j];
                Y[k] = Y[j];
                j++;
            } else if (j > hi) {
                out[k] = in[i];
                X[k] = X[i];
                Y[k] = Y[i];
                i++;
            } else if (less(in[j], in[i])) {
                out[k] = in[j];
                X[k] = X[j];
                Y[k] = Y[j];
                j++;
            } else {
                out[k] = in[i];
                X[k] = X[i];
                Y[k] = Y[i];
                i++;
            }
        }
    }

    private static void BUmergeSort(Comparable[] a){
        Comparable[] src = a, dst = new Comparable[a.length], tmp;
        int N = a.length;
        for (int n = 1; n < N; n *= 2){
            for (int i = 0; i < N; i += 2 * n){
                mergeBU(src, dst, i, i + n - 1, Math.min(i + 2 * n - 1, N - 1));
            }
            tmp = src; src = dst; dst = tmp;
        }
        if (src != a) System.arraycopy(src, 0, a, 0, N);
    }

    private static void K_BUmergeSort(Comparable[] a){
        Comparable[] src = a, dst = new Comparable[a.length], tmp;
        int N = a.length;
        for (int n = 1; n < N; n *= 2){
            for (int i = 0; i < N; i += 2 * n){
                mergeBU(src, dst, i, i + n - 1, Math.min(i + 2 * n - 1, N - 1));
            }
            tmp = src; src = dst; dst = tmp;
        }
        if (src != a) System.arraycopy(src, 0, a, 0, N);
    }

    public static void main(String args[]){
        HW1 hw = new HW1();
        hw.link();
        hw.calDistance();

        System.out.println("기본적인 방법");

        long start_1 = System.currentTimeMillis();
        hw.ShellSort(distance);
        long end_1 = System.currentTimeMillis();

        int real_K = (k == -1) ? n : k;
        System.out.println("k = " + real_K + "일 때의 기본적인 방법 정렬 시간: " + (end_1 - start_1) + "ms");
        hw.show(distance);


        System.out.println("개선된 방법");

        int size = distanceAdv.length;
        long start_2 = System.currentTimeMillis();
        if (k == -1) {
            hw.BUmergeSort(distanceAdv);
        } else if (size <= 10) {
            hw.K_SelectionSort(distanceAdv);
        } else if (size <= 1000) {
            hw.K_ShellSort(distanceAdv);
        } else if (size <= 100000) {
            hw.K_TDmergeSort(distanceAdv);
        } else {
            hw.K_BUmergeSort(distanceAdv);
        }
        long end_2 = System.currentTimeMillis();

        System.out.println("k = " + real_K + "일 때의 개선된 정렬 시간: " + (end_2 - start_2) + "ms");
        hw.show(distanceAdv);
    }
}
