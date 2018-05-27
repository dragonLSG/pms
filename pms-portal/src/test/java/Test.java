import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {


        Date today = new Date();
        today.setDate(1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = dateFormat.format(today);

        System.out.println();
    }
}
