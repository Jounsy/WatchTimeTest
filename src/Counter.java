/**
 * Created by Work-TESTER on 03.07.2017.
 */
public class Counter {
    public Double count(double a) {
        for (int i = 0; i < 1000000; i++) {
            a = a + Math.tan(a);
        }

        return a;
    }
}
