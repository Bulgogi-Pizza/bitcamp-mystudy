package study.io;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class Test01 {
  public static void main(String[] args) {
    ByteArrayOutputStream in = new ByteArrayOutputStream();

    int size = 10;

    byte[] a = size.getBytes();

    String name = "가가";

    name.getBytes();

    System.out.println(name.getBytes(StandardCharsets.UTF_8));
  }
}
