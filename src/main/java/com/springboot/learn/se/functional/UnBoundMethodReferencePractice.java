/**
 * UnBoundMethodReferencePractice
 * 
 * 通过未绑定的方法引用，可以间接地调用对象的方法，虽然没什么用。
 */
public class UnBoundMethodReferencePractice {

    public static void main(String[] args) {
        TestInterface ti=Test::test;
        Test test=new Test();
        ti.test(test);
    }
}

class Test{
    public void test(){
        System.out.println("Test::test");
    }
}

interface TestInterface{
    void test(Test test);
}