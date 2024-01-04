import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class test {

  public static void main(String[] args) {

    int totalArr = 100000;
    int[] rangeArr = new int[] {10, 20};

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("../bin/data.txt"));
        BufferedOutputStream binout =
            new BufferedOutputStream(new FileOutputStream("../bin/data.dat"));
        BufferedOutputStream posout =
            new BufferedOutputStream(new FileOutputStream("../bin/pos.dat"))) {
      long pos = 0; // long because total element in 3M > int = 2^31.

      for (int i = 1; i <= totalArr; i++) {
        int size = random.nextInt(rangeArr[1] - rangeArr[0] + 1) + rangeArr[0];

        // writer.write(String.valueOf(i) + " ");

        byte[] posBytes = toBytes(pos, 8);
        posout.write(posBytes);
        pos += size;

        for (int j = 0; j < size; j++) {
          int element = random.nextInt();
          byte[] elementBytes = toBytes(element, 4);
          binout.write(elementBytes);

          writer.write(String.valueOf(element) + " ");
        }
        writer.newLine();

        if (i == totalArr) {
          byte[] lastPosBytes = toBytes(pos, 8);
          posout.write(lastPosBytes);
        }
      }
      System.out.println("Done");
    } catch (IOException e) {
      System.err.println("Error write file: " + e.getMessage());
    }
  }

  private static final Random random = new Random();

  // (int || long ) -> byte (a = total byte)
  private static byte[] toBytes(long value, int a) {
    byte[] result = new byte[a];
    for (int i = 0; i <= (a - 1); i++) {
      result[i] = (byte) (value >> (8 * (a - 1 - i)));
    }
    return result;
  }
}
