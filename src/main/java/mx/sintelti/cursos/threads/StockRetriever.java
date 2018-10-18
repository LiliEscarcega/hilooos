package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.math.BigDecimal;

public class StockRetriever implements  Runnable {

    private String company;

    public StockRetriever(String company) {
        this.company = company;
    }

    private BigDecimal getStockPrice() throws IOException {
        Stock stock = YahooFinance.get(company);
        BigDecimal price = stock.getQuote().getPrice();
        TestStock.sumaTotal = TestStock.sumaTotal.add(price);
        System.out.println(stock.getSymbol() + "-"+stock.getQuote().getPrice());
        return price;
    }

    @Override
    public  void run () {
        try {
            getStockPrice();
        }
        catch (IOException e){
            System.out.println("ERROR DE DANN");
        }
    }
}