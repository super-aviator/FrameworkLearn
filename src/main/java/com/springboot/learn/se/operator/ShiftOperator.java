/**
 * ShiftOperator
 * 
 * On Java 8 书中原文：
 *      移位运算符面向的运算对象也是二进制的“位”。可单独用它们处理整数类型（基本类型的一种）。
 *      左移位运算符 << 能将其左边的运算对象向左移动右侧指定的位数（在低位补 0）。右移位运算符 >> 则相反。
 *      位移运算符有“正”、“负”值”：若值为正，则在高位插入 0；若值为负，则在高位插入 1。
 *      Java 也添加了一种“不分正负”的右移位运算符（>>>），它使用了“零扩展”（zero extension）：无论正负，都在高位插入 0。
 *      这一运算符是 C/C++ 没有的。
 *      
 *      如果移动 char、byte 或 short，则会在移动发生之前将其提升为 int，结果为 int。
 *      仅使用右侧的 5 个低阶位。这可以防止我们移动超过 int 范围的位数。若对一个 long 值进行处理，最后得到的结果也是 long。
 * 
 * 
 * 短短的两段话，解决了我记忆移位运算符的困扰。佩服佩服。
 * 
 */
public class ShiftOperator {

    public static void main(String[] args) {
        int i= 0b100001;
        System.out.println(Integer.toBinaryString(i<<4));
    }
}

// output:
//     1000010000