/**
 * TryWithResource
 * 
 * 在使用try-with-resources语句的时候，异常可能发生在try语句中，也可能发生在释放资源时。
 * 如果资源初始化时或try语句中出现异常，而释放资源的操作正常执行，try语句中的异常会被抛出；
 * 如果try语句和释放资源都出现了异常，那么最终抛出的异常是try语句中出现的异常，
 * 在释放资源时出现的异常会作为被抑制的异常添加进去，即通过Throwable.addSuppressed方法来实现。
 * 
 * 1. try-with-resources块中如果在try块中和close()方法中都抛出了异常，此时close块中的异常会被抑制，
 * 最终只会抛出try块中的异常，可以使用Throwable.getSuppressed得到被抑制的异常。
 */
public class TryWithResource {

    public static void main(String[] args) {

    }
}