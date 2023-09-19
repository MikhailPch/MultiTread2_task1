import java.util.*;


public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

        int numberOfRoutes = 1000;
        for (int i = 0; i < numberOfRoutes; i++) {
            new Thread(() -> {

                String route = generateRoute("RLRFR", 100);
                int countOfR = countChar(route, 'R');

                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(countOfR)) {
                        sizeToFreq.put(countOfR, sizeToFreq.get(countOfR) + 1);
                    } else {
                        sizeToFreq.put(countOfR, 1);
                    }
                }
            }).start();
        }

        int maxValue = Collections.max(sizeToFreq.values());

        sizeToFreq.forEach((key, value) -> {
            if (value == maxValue) {
                System.out.println("Самое частое количество повторений " + key + " (встретилось "
                        + maxValue + " раз)");
            }
        });
        System.out.println("Другие размеры:");
        sizeToFreq.forEach((key, value) -> {
            if (value != maxValue) {
                System.out.println("- " + key + " (" + value + " раз)");
            }
        });
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    private static int countChar(String str, char ch) {
        return (int) str.chars()
                .filter(c -> c == ch)
                .count();
    }
}