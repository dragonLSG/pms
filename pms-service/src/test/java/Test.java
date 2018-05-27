import java.util.Random;

public class Test {
    public static void main(String[] args) {
        //生成验证码
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        System.out.println(stringBuilder.toString());
    }
}
