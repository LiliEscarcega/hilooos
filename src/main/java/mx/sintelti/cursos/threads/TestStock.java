package mx.sintelti.cursos.threads;

import yahoofinance.Stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestStock {
    public static void main(String[] args) throws IOException {
        long inicio = System.nanoTime();
        List<String> lista = leerArchivo();
        BigDecimal sumaTotal = new BigDecimal("0.00");
        StockRetriever stockRetriever = new StockRetriever("FB");
        System.out.println("FB" + " " + stockRetriever.getStockPrice());
        for (String x : lista) {
            StockRetriever iterar = new StockRetriever(x);
            sumaTotal = sumaTotal.add(iterar.getStockPrice());
        }
        System.out.println("MARKET CAP: " + sumaTotal);
        long fin = System.nanoTime();
        System.out.println("TIEMPO TOTAL: " + ((fin - inicio) / 1000000000.0) + "s");
    }

    public static List<String> leerArchivo() throws IOException {
        String fileName = "/Users/LENOVO/IdeaProjects/hilooos/src/main/resources/list.txt";
        List<String> lineas = Files.readAllLines(Paths.get(fileName));
        return lineas;
    }
}
