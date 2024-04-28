## Summary : 
This project aims to create a web crawler to scan webpages for links to other websites. Instead of using single threads, it takes advantage of multithreading in order to travel different
paths to source as many links as possible concurrently (divide and conquer). The programming language
chosen to create this project is Java, due to its multithreading capabilities and the fact that I already have
a solid understanding of this language; along with the Jsoup reference library. The program uses the theme of a virtual pet to complete this task
because web crawlers are also called spiders. So, the program involves some interactivity and
personalization from the user.
## Input : 
Using console prompts, the user will be asked to provide a name for the spider, how deep
should the spider search (you are able to input a number between 1 and 3, however for testing purposes I
would recommend 1 to minimize runtime), and the initial website the spider should start the scan at. The
WebCrawler object will take these parameters and will begin the search. During the search, there is an
animation of a spider crawling.
## Output : 
Once the crawl is done, the user will be notified and given the number of links accessed.
There will be another question asking if the user would like to see the list of visited links, you can respond
yes or no. Finally, this is a goodbye message from your spider.
