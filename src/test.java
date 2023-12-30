import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class test {

  public static void main(String[] args) {

    int totalArr = 300;
    int[] rangeArr = new int[] {1, 10};

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("../bin/data.txt"));
        BufferedOutputStream binout = new BufferedOutputStream(new FileOutputStream("../bin/data.dat"));
        BufferedOutputStream posout = new BufferedOutputStream(new FileOutputStream("../bin/pos.dat"))) {
      long pos = 0; // long vì tổng số element của 3 triệu mảng > int = 2^31.

      for (int i = 1; i <= totalArr; i++) {
        int size = random.nextInt(rangeArr[1] - rangeArr[0] + 1) + rangeArr[0];

        writer.write(String.valueOf(i) + " ");

        byte[] posBytes = toBytes(pos, 8);
        posout.write(posBytes);
        pos += size;

        for (int j = 0; j < size; j++) {
          int element = random.nextInt();
          byte[] elementBytes = toBytes(element, 4);
          binout.write(elementBytes);

          writer.write(String.valueOf(element) + " ");
        }

        if (i == totalArr) {
          byte[] lastPosBytes = toBytes(pos, 8);
          posout.write(lastPosBytes);
        }

        writer.newLine();
      }
      System.out.println("done");
    } catch (IOException e) {
      System.err.println("Lỗi khi viết vào tệp: " + e.getMessage());
    }
  }

  private static final Random random = new Random();

  // Phương thức chuyển đổi thành mảng byte (a = số byte)
  private static byte[] toBytes(long value, int a) {
    byte[] result = new byte[a];
    for (int i = 0; i <= (a - 1); i++) {
      result[i] = (byte) (value >> (8 * (a - 1 - i)));
    }
    return result;
  }
}
