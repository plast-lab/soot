import java.util.Vector;


public class AddAssignTest {

    String abc = "abcdefghijklmnopqrstuvwxyzJ";
    private Vector charSet = new Vector(27);

    {
        for (int i = 0; i < abc.length(); i++) {
            charSet.add((new Character(abc.charAt(i))).toString());
        }
    }

    public static void main(String[] args) {
        AddAssignTest aat = new AddAssignTest();
        aat.run();
    }

    private void run() {
        String result = new String();

        result += this.charSet.elementAt(3);
    }
}
