import java.util.Arrays;
import java.util.Objects;

/**
 * Equals
 * 
 * 1. equals方法必须遵循的5大原则，缺一不可： 1.1 自反性-对于任何非空引用 x，x.equals(x) 必须返回 true。 1.2
 * 对称性-对于任何非空引用 x 和 y，如果且仅当 y.equals(x) 返回 true 时 x.equals(y) 必须返回 true。 1.3
 * 传递性-对于任何非空引用 x、y、z，如果 x.equals(y) 返回 true，y.equals(z) 返回 true，则 x.equals(z)
 * 必须返回 true。 1.4 一致性-对于任何非空引用 x 和 y，如果在 equals 比较中使用的信息没有修改，则 x.equals(y)
 * 的多次调用必须始终返回 true 或始终返回 false。 1.5. 对于任何非空引用 x，x.equals(null) 必须返回 false。
 * 
 * 2. 没有令人满意的方法来继承一个可实例化的类并添加一个值组件，但是有一个很好的变通方法：按照条目 18 的建议，“优先使用组合而不是继承”。
 * 
 * 3. java.sql.Timestamp 继承了 java.util.Date 并添加了一个 nanoseconds 字段。 Timestamp 的等价
 * equals 确实违反了对称性，并且如果 Timestamp 和 Date 对象在同一个集合中使用，或者以其他方式混合使用，则可能导致不稳定的行为。
 * 
 * 4. 什么情况不需要重写equals方法 重写 equals
 * 方法看起来很简单，但是有很多方式会导致重写出错，其结果可能是可怕的。避免此问题的最简单方法是不覆盖 equals
 * 方法，在这种情况下，类的每个实例只与自身相等。 如果满足以下任一下条件，则说明是正确的做法： 每个类的实例都是固有唯一的。 对于像 Thread
 * 这样代表活动实体而不是值的类来说，这是正确的。 Object 提供的 equals 实现对这些类完全是正确的行为。
 * 类不需要提供一个“逻辑相等（logical equality）”的测试功能。例如 java.util.regex.Pattern 可以重写 equals
 * 方法检查两个是否代表完全相同的正则表达式 Pattern 实例， 但是设计者并不认为客户需要或希望使用此功能。在这种情况下，从 Object 继承的
 * equals 实现是最合适的。 父类已经重写了 equals 方法，则父类行为完全适合于该子类。例如，大多数 Set 从 AbstractSet 继承了
 * equals 实现、List 从 AbstractList 继承了 equals 实现，Map 从 AbstractMap 的 Map 继承了
 * equals 实现。 类是私有的或包级私有的，可以确定它的 equals 方法永远不会被调用。如果你非常厌恶风险，可以重写 equals
 * 方法，以确保不会被意外调用：
 */
public class Equals10 {

    public static void main(String[] args) {
    }
}

class PhoneNumber {
    public final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = checkRange(areaCode, 999, "areaCode");
        this.prefix = checkRange(prefix, 999, "prefix");
        this.lineNum = checkRange(lineNum, 999, "lineNum");
    }

    public static short checkRange(int val, int max, String msg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(msg + " : " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Ojbect obj) { // 在 equal 时方法声明中，不要将参数 Object 替换成其他类型。非常重要
        if (obj == this) // 步骤一，直接判断引用，引用的同一个对象时直接返回true.
            return true;

        if (!(obj instanceof PhoneNumber)) // 步骤二，判断是否是同一类型，这一步中不需要再判断obj==null,因为null instanceof 任何类型都是false.
            return false;

        PhoneNumber pn = (PhoneNumber) obj; // 步骤三，直接转换为同一类型

        return pn.areaCode == this.areaCode && pn.prefix == this.prefix && pn.lineNum == this.lineNum; // 步骤四，对对象中的成员变量逐一进行对比

        /*
         * 对于类型为非 float 或 double 的基本类型，使用 == 运算符进行比较； 对于对象引用属性，递归地调用 equals 方法；对于 float
         * 基本类型的属性 ，使用静态 Float.compare(float, float) 方法；对于 double 基本类型的属性， 使用
         * Double.compare(double, double) 方法。由于存在 Float.NaN，-0.0f 和类似的 double 类型的值，
         * 所以需要对 float 和 double 属性进行特殊的处理；
         */

        /*
         * Float.equals 方法的详细文档。 虽然你可以使用静态方法 Float.equals 和 Double.equals 方法对 float 和
         * double 基本类型的属性进行比较， 这会导致每次比较时发生自动装箱，引发非常差的性能。 对于数组属性，将这些准则应用于每个元素。
         * 如果数组属性中的每个元素都很重要， 请使用其中一个重载的 Arrays.equals 方法。
         */

        /*
         * 某些对象引用的属性可能合法地包含 null。 为避免出现 NullPointerException 异常，请使用静态方法
         * Objects.equals(Object, Object) 检查这些属性是否相等。
         */
    }
}