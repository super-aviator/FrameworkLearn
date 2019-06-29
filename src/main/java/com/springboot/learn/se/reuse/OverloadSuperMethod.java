/**
 * OverloadSuperMethod
 * 
 * 在子类中重载父类的方法不会屏蔽父类的方法
 */
public class OverloadSuperMethod {

    public static void main(String[] args) {
        DeriveClass dc=new DeriveClass();
        dc.method(6.66f);
        dc.method(666);
    }
}

class SuperClass{
    public void method(float f){
        System.out.println(f);
    }
}

class DeriveClass extends SuperClass{
    public void method(int i){
        System.out.println(i);
    }
}