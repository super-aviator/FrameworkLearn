import java.util.function.*;
/**
 * FunctionalInterface
 * 
 * 函数式接口：接口中有且仅有一个方法的接口
 * @FunctionalInterface 可以在只有一个方法的接口中注解，表示此接口是一个函数式接口。
 * 如果接口中的抽象方法多于一个，则编译会报错。
 */
public class FunctionalAnnotation {

    public static void main(String[] args) {
    }
}

@FunctionalInterface
interface FunctionalInterfaceAnnotation{
    String message(String msg);
}

/* @FunctionalInterface
interface NotFunctionalInterface{
    String message(String msg);
    String other(String msg);
} */