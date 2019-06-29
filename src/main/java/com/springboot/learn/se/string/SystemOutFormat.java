/**
 * SystemOutFormat
 * 
 * System.out.format和可以格式化字符串，并且打印该字符串，同时返回一个pringStreams
 * %n和/n功能一样，不过前者是跨平台的，后者不是，非常的nice
 */
public class SystemOutFormat {

    public static void main(String[] args) {
        System.out.format("%d %n %d", 1,2);
    }
}