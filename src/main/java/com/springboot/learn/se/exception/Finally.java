/**
 * Finally
 * 
 * finally块一般一定会执行，唯一一个例外是遇到System.exit(),程序会直接退出，不能运行finally块也情有可原。
 */
public class Finally {

    public static void main(String[] args) {
        try {
            System.out.println("准备测试finally块");
            System.exit(1);
        } finally {
            System.out.println("finally块会执行吗");
        }
    }
}