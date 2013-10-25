package feed4j;

import it.sauronsoftware.feed4j.FeedIOException;
import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.FeedXMLParseException;
import it.sauronsoftware.feed4j.UnsupportedFeedException;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.io.IOException;
import java.net.URL;

public class Test {

	public static void main(String[] args) throws FeedIOException, FeedXMLParseException, UnsupportedFeedException, IOException {
		Feed parse = FeedParser.parse(new URL("http://www.eetimes.com/RSS/RSSResult?contentType=1&contentSubtype=News&classificationGroupId=0"));
		
		FeedItem item = parse.getItem(1);
		
		System.out.println(item.getPubDate());
		
		
		
//		for (int i = 0; i < parse.getItemCount(); i++) {
//			FeedItem item = parse.getItem(i);
//			String title = item.getTitle();
//			System.out.println(item.getGUID() + " "+ title);
//		}
		
	
	}
	
}
