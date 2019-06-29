/**
 * BitwiseOperator
 * 
 * 按位运算符和逻辑运算符都可以作用于boolean类型，但是按位运算符不会有短路，逻辑运算符会有短路。
 * 所以以后想要屏蔽短路的话可以使用按位运算符代替逻辑运算符
 */
public class BitwiseOperator {

    public boolean m1(){
        System.out.println("m1 has executed");
        return true;
    }

    public boolean m2(){
        System.out.println("m1 has executed");
        return true;
    }

    public static void main(String[] args) {
        BitwiseOperator stepByStepOperators=new BitwiseOperator();
        System.out.println(stepByStepOperators.m1()|stepByStepOperators.m2());
        System.out.println(stepByStepOperators.m1()||stepByStepOperators.m2());
    }
}

// output:
//     m1 has executed
//     m1 has executed
//     true
//     m1 has executed
//     true