/**
 * UnBoundMethodReference
 * 未绑定的方法引用是指没有关联对象的普通（非静态）方法。 要使用未绑定的引用，你必须提供以下对象：
 * 
 * 使用未绑定的引用时，函数方法的签名（接口中的单个方法）不再与方法引用的签名完全匹配。 有一个很好的理由说服你，那就是你需要一个对象来调用方法。
 * 
 * This::twoArgs、This::threeArgs、This::fourArgs是三个未绑定的方法引用（未绑定指的是位绑定到对象上），我们可以将它赋值给一个引用，此时可以
 * 允许方法参数不匹配，而在调用时需要将对象填充进去
 */
public class UnBoundMethodReference {

    public static void main(String[] args) {
        TwoArgs twoArgs=This::twoArgs;
        ThreeArgs threeArgs=This::threeArgs;
        FourArgs fourArgs=This::fourArgs;
        This athis=new This();
        twoArgs.twoArgs(athis,1,2);
        threeArgs.threeArgs(athis,1,2,3);
        fourArgs.fourArgs(athis,1,2,3,4);
    }
}

class This{
    public void twoArgs(int arg1,int arg2){
        System.out.println("This::twoArgs "+arg1+" "+arg2);
    }

    public void threeArgs(int arg1,int arg2,int arg3){
        System.out.println("This::twoArgs "+arg1+" "+arg2+" "+arg3);
    }

    public void fourArgs(int arg1,int arg2,int arg3,int arg4){
        System.out.println("This::twoArgs "+arg1+" "+arg2+" "+arg3+" "+arg4);
    }
}

interface TwoArgs{
    void twoArgs(This athis,int arg1,int arg2);
}

interface ThreeArgs{
    void threeArgs(This athis,int arg1,int arg2,int arg3);
}

interface FourArgs{
    void fourArgs(This athis,int arg1,int arg2,int arg3,int arg4);
}