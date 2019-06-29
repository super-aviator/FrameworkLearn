import java.io.*;

/**
 * WrapCheckedException 
 * 为了避免受检异常所带来的麻烦，可以将受检异常包装进运行时异常之中
 * 然后就不需要为受检异常编写处理代码，同时在需要捕获时，调用getCause()方法得到原始的受检异常的信息
 * 
 * 异常处理的终极名言： 只有在知道如何处理异常的情况下才捕获异常，否则就向上抛出，避免异常被吞并。
 */
public class WrapCheckedException {

    public void getAWrapedCheckedException(int i) {
        try {
            switch (i) {
            case 1:
                throw new FileNotFoundException();
            case 2:
                throw new IOException();
            case 3:
                throw new RuntimeException("who am i?i`m nobody");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);// 使用非受检异常对受检异常进行包装
        }
    }

    public static void main(String[] args) {
        WrapCheckedException wce = new WrapCheckedException();

        for (int i = 0; i < 4; i++) {
            try {
                wce.getAWrapedCheckedException(i);
            } catch (RuntimeException e) {
                Throwable t = e.getCause();
                t.printStackTrace();
            }
        }
    }
}