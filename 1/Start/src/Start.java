import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;

class Start{

    public static final double START = 1.0, FINISH = 123.4, STEP = 0.5, STEP_ADD = 0.2;

    public static void sum(double[] tab){

        double sum = 0;

        for(int i = 0; i < tab.length; i++){

            sum += tab[i];

        }

        System.out.println("suma = " + sum);
        average(tab, sum);

    }

    public static void average(double[] tab, double sum){

        double average = sum / tab.length;
        System.out.println("srednia = " + average);
        standardDeviation(tab, average);

    }

    public static void standardDeviation(double[] tab, double average){

        double standardDeviation;
        double temp = 0;
        int N = tab.length;

        for(int i = 0; i < N; i++){

            temp += ((average - tab[i]) * (average - tab[i])) / (N * (N - 1));

        }

        standardDeviation = sqrt(temp);
        System.out.println("odchylenie std. sredniej = " + standardDeviation);

    }

    public static void main(String[] args){

        List<Double> numbers = new ArrayList<>();
        double TEMP = START;
        double TEMP2 = STEP;
        numbers.add(START);

        while(TEMP + TEMP2 <= FINISH){

            TEMP += TEMP2;
            numbers.add(TEMP);
            TEMP2 += STEP_ADD;

        }

        double[] tab = numbers.stream().mapToDouble(Double::doubleValue).toArray();
        sum(tab);

    }

}
