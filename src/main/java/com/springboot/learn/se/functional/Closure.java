import java.util.ArrayList;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.List;

/**
 * Closure 闭包
 * 
 * 闭包指的是返回lambda的方法中，lambda引用了外部的变量，这种情况就叫闭包。
 * 如果语言不能自动解决这个问题，那将变得非常具有挑战性。 能够解决这个问题的语言被称为支持闭包。
 * 
 * 为了满足闭包，需要满足以下几个要求
 * 1. lambda中引用的局部变量必须是 final 或者 实际的 final （effectively final）实际的final是指需要保证这个变量不能被修改，否则编译报错。
 * 2. lambda中引用的局部变量的集合或者其他引用类型,需要保证引用指向的对象不变，而对象本身的内容是可以变的。
 * 3. lambda中如果引用的是外部类的成员变量，则不需要使用上边的闭包原则，可以随意更改，并且调用方法产生的多个函数共享对象的这个成员变量。
 */
public class Closure {
    int closurei=10;
    ArrayList<Integer> list=new ArrayList<>();

    public IntSupplier getIntSupplier(int x){ //[1]
        int i=1;                //如果不改变局部变量，则i实际是final的，只是没有显式说明。

        // final int i=1;       //error 显式的使用final也是可以的。
        // return ()->x+i++;    //error 这里的i必须为final或者实际上是final的。
        // x++;                 //error 方法传进来的参数同样需要使用闭包原则。
        return ()->x+i;
    }

    public Supplier<List<Integer>> getCollectionSupplier(){//[2]
        ArrayList<Integer> list=new ArrayList<>();      //ok 这个可以有
        list.add(1);
        return ()->list;
    }

    public IntSupplier getIntSupplierWithOuterClass(){ //[3]
        closurei++;
        return ()->closurei;
    }

    public Supplier<List<Integer>> getCollectionSupplierWithOutClass(){//[3]
        list.add(1);
        return ()->list;
    }


    public static void main(String[] args) {
        Closure c=new Closure();
        IntSupplier is1=c.getIntSupplier(1);
        System.out.println(is1.getAsInt());

        Supplier<List<Integer>> is2=c.getCollectionSupplier();
        System.out.println(is2.get());
        Supplier<List<Integer>> is22=c.getCollectionSupplier();
        System.out.println(is22.get());//由于list是在方法中创建，所以不会共享，每个函数拥有一个list对象

        IntSupplier is3=c.getIntSupplierWithOuterClass();
        System.out.println(is3.getAsInt());

        Supplier<List<Integer>> is4=c.getCollectionSupplierWithOutClass();
        System.out.println(is4.get());
        Supplier<List<Integer>> is44=c.getCollectionSupplierWithOutClass();
        System.out.println(is4.get());//所有函数共享外部对象的list
    }
}

/* output:
2
[1]
[1]
11
[1]
[1, 1] */