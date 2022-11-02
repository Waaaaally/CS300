import java.util.Random;

public class RandIntTestingBcImDumbLMFAO {
    public static void main(String[] args){
        Random randGen = new Random();
        for (int i = 0; i < 50; i++){
            float random = randGen.nextFloat() * 2 - 1;
            System.out.println(random);
        }
    }
}
