/**
 * BinaryLiteral
 * 
 * 字面常量：
 *      long:L/l
 *      float：F/f
 * 前导：
 *      八进制：0
 *      十六进制：0x:0X
 * 
 * Java7中新增了二进制字面常量(Literal):0b/0B,可以直接赋值到对应的数值类型中,需要注意取值范围。
 * */
public class BinaryLiteral {

    public static void main(String[] args) {
        byte i=0b1111111;
        // float i=0b11111111;
        System.out.println(i);
        // System.out.println(Integer.toBinaryString(i));
    }
}