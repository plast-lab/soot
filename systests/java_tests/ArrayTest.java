public class ArrayTest {

    public boolean[][] my_array = {

            {true, false, true},
            {false, true, true},
            {},
            {},
            {false, false, true}
    };

    public static void main(String[] args) {
        ArrayTest a = new ArrayTest();
        System.out.println(a.my_array[0][0]);
    }


}
