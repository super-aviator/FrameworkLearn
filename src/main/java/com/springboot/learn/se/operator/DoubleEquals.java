/**
 * Equals
 * 基本类型的包装类型和原始类型都是可以用==比较的,都放在常量池中，引用都指向同一个对象，这都有点不清楚，惭愧惭愧。
 */
public class DoubleEquals {
    public static void main(String[] args) {
        Long a=1l;
        Long b=1l;
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }
}