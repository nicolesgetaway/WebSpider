import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpiderTester {
    public static void main(String[] args) {
        //variables
        ExecutorService exe = Executors.newFixedThreadPool(3);
        StringBuilder result = new StringBuilder();
        int count = 1;
        String name;
        Scanner scan = new Scanner (System.in);
        int depth;
        String link;

        System.out.println("----\t\tCreate Your Own Spider!\t\t----");
        System.out.print("What will your spider's name be? >> ");
        name = scan.next();
        System.out.print("How deep to do you want " + name + " to search? (a number from 1 to 3) >> ");
        depth = scan.nextInt();
        while (depth >= 4 || depth < 1){
            System.out.print("Invalid. Must be between 1 and 3 >> ");
            depth = scan.nextInt();
        }
        System.out.print("What link do you want " + name + " to start at? >> ");
        link = scan.next();
        while (!link.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)")){
            System.out.println("Invalid link. Try again >>> ");
            link = scan.next();
        }

        try {
            Future<List<String>> test = exe.submit(new WebCrawler(link, depth));
            System.out.println("Starting crawl...");
            System.out.println("           _ ");
            System.out.println("          ( )     Look at " + name + " go!");
            while (!test.isDone()){
                System.out.print("\r..........<\\|oo>");
                Thread.sleep(100);
                System.out.print("\r..........(|)oo\\");
                Thread.sleep(100);
                System.out.print("\r..........|)>oo|");
                Thread.sleep(100);
                System.out.print("\r........../>\\oo)");
                Thread.sleep(100);
            }
            System.out.println("\nFinished crawl.");
            List<String> res1 = test.get();
            System.out.print("\nThere are " + res1.size() +" links.\nDo you want to see the links visited? (Y or N) > ");
            String choice = scan.next();
            if (choice.equals("Y")){
                for (String str : res1){
                    result.append(count + "\t" + str);
                    count ++;
                    System.out.println(result.toString());
                    result.setLength(0);
                }
            }
            System.out.println("Thank you, bye! -" + name);
        }
        catch (Exception e) {
            e.getLocalizedMessage();
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
