public class StaticConstantsTest {

    public static int MAX = 10;

    static {
        System.out.println();
    }

    public int x = 10;

    {
        System.out.println("hi");
    }

    public StaticConstantsTest() {
        System.out.println(x);
    }

    public StaticConstantsTest(int i) {
        System.out.println(i * x);
    }

    public static void main(String[] args) {
        StaticConstantsTest sct = new StaticConstantsTest();
        sct.run();
    }

    public void run() {

        int[] arr = new int[MAX];
        int i = x;
        x = i * i;
    }
} 
