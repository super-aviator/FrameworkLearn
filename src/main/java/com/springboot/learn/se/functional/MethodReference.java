/**
 * MethodReference
 * 
 * 1. 方法引用中，方法的匹配规则包含了包含了返回值,而在方法重载中则不包含返回值（只包含参数，方法名，参数顺序） 方法引用。
 *    方法匹配规则中没有包含方法名，这与匿名内部类方式不同，匿名内部类必须实现此接口，即方法签名必须完全一样。
 * 2. 方法引用是响应式的，将某个对象的方法赋值给方法引用，对象的修改会响应给方法引用。
 */
public class MethodReference {

    public static class InnerClass {
        private String idea = "original";

        public void changeIdea(String idea) {
            this.idea = idea;
        }

        public void perform1(String msg) {
            System.out.println(idea + "---" + msg);
        }

        public String perform2(String msg) {
            System.out.println(idea + "---" + msg);
            return "";
        }
    }

    public static void main(String[] args) {
        InnerClass ic = new InnerClass();
        Callable c = ic::perform1;
        // Callable c = ic::perform2;
        c.call("xqk");
        ic.changeIdea("changed");
        c.call("xqk");
    }
}

/**
 * InnerMethodReference
 */
interface Callable {

    void call(String str);
}

/* output:
original---xqk
changed---xqk */