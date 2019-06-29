/**
 * CallSuperContructor
 * 
 * 1：在导出类构造器中，如果不显式调用基类构造器，那么在导出类构造器中编译器会
 *    自动插入调用基类无参构造器的代码，此时如果基类因为只重写了带参构造器而使
 *    得没有了无参构造器，编译器就会报错。
 * 
 * 2：导出类调用基类构造器的super()代码必须放在构造器的第一行，防止产生不安全行为。
 */
public class CallSuperContructor {

    public static void main(String[] args) {
        DeriveClass dc=new DeriveClass(666);
    }
}

class SuperClass{

    public SuperClass(){
        System.out.println("SuperClass Constructor");
    }

    public SuperClass(int i){
        System.out.println("SuperClass Constructor with args");
    }
}

class DeriveClass extends SuperClass{

    public DeriveClass(){
        System.out.println("DeriveClass Constructor");
    }

    public DeriveClass(int i){
        System.out.println("DeriveClass Constructor with args");
        // Super(i);
    }
} 