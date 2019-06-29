/**
 * Java 7 中有一个深思熟虑的补充：我们可以在数字文字中包含下划线 _，以使结果更清晰。这对于大数值的分组数字特别有用。
 * 
 * 使用规则：
 *      1：仅限单 _，不能多条相连。
 *      2：数值开头和结尾不允许出现 
 *      3：F、D 和 L的前后禁止出现 _。
 *      4：二进制前导 b 和 十六进制 x 前后禁止出现 _。
 */
public class Underscores {
    public static void main(String[] args) {
        double d = 341_435_936.445_667;
        System.out.println(d);
        int bin = 0b0010_1111_1010_1111_1010_1111_1010_1111;
        System.out.println(Integer.toBinaryString(bin));
        System.out.printf("%x%n", bin); // [1]
        long hex = 0x7f_e9_b7_aa;
        System.out.printf("%x%n", hex);
    }
}