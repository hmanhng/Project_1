import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AccessArrays {

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.print("Input index: ");
    int index = sc.nextInt();
    sc.close();

    getArray(index);
  }

  public static void getArray(int index) throws IOException {
    try (RandomAccessFile pos = new RandomAccessFile("../bin/pos.dat", "r");
        RandomAccessFile dat = new RandomAccessFile("../bin/data.dat", "r")) {

      index--;
      pos.seek(index * 8); // pos.dat luu du lieu long
      long posData = pos.readLong();
      long size = pos.readLong() - posData;

      // System.out.print(posData + " " + size + "\n");
      dat.seek(posData * 4); // data.dat luu du lieu int
      for (long i = 1; i <= size; i++) {
        int element = dat.readInt();
        System.out.print(element + " ");
      }
    }
  }
}
