package mx.sintelti.cursos.threads;
import yahoofinance.Stock;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TestStock {
    private static volatile BigDecimal totalPrice = new BigDecimal(0);
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "/Users/LENOVO/IdeaProjects/hilooos/src/main/resources/list.txt";
        try {
            int cores = Runtime.getRuntime().availableProcessors();
            System.out.println("CPU Cores: " + cores);
            double blockingCoefficient = 0.9;
            int poolSize = (int) (cores / (1 - blockingCoefficient));

            System.out.println("CPU Cores : " + cores);
            System.out.println("Pool Size : " + poolSize);

            List<String> lineas = Files.readAllLines(Paths.get(fileName));
            long inicio = System.nanoTime();
            Collection<Callable<Object>> tareas = new ArrayList<>();
            for (String linea : lineas) {
                StockRetriever stockRetriever = new StockRetriever(linea.trim());
                tareas.add(Executors.callable(stockRetriever));
            }
            ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
            threadPool.invokeAll(tareas); //invoca todos
            long fin  = System.nanoTime();
            System.out.println("Timpo total: " + ((fin-inicio)/1000000000.0) + "s");
                threadPool.shutdown();
        } catch (IOException ioe){
            System.out.println("Error al manipular archivo");
        }
    }
    public static synchronized void addPrice (BigDecimal price){
        totalPrice = totalPrice.add(price);
    }
}

