import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.Callable;
import org.jsoup.*;
import org.jsoup.nodes.*;


public class WebCrawler implements Callable<List<String>> {
    //instance variables
    private String initLink;
    private final int depth;
    private static final Set<String> visited = ConcurrentHashMap.newKeySet();


    //constructor
    public WebCrawler(String link, int max_depth) {
        System.out.println("Successfully created webcrawler.");
        initLink = link;
        depth = max_depth;
    }
    
    //methods
    public void crawl(String link, int level){
            Document doc = request(link);
            if (doc != null){
                //for loop to search html document for links
                try {
                    for (Element url : doc.select("a[href]")){
                        String next = url.absUrl("href");
                        if (visited.add(next) && level <= depth){
                            crawl(next, level++);
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                Thread.currentThread().interrupt();
                return;
        }
    }

    @Override
    public List<String> call() throws Exception {
        // TODO Auto-generated method stub
        List<String> result = new ArrayList<>();
        try {
            crawl(initLink, 1);
            result = getResultURLs();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    private Document request(String url){
        try {
            Connection c = Jsoup.connect(url);
            Document d = c.get();
            if (c.response().statusCode() == 200 && url.length() <= 256){
                visited.add(url);
                return d;
            }
            return null;
        }
        catch (IOException e){
            return null;
        } 
    }
    private List<String> getResultURLs(){
        return new ArrayList<>(visited);
    }
    public int getTotal(){
        return getResultURLs().size();
    }
}
