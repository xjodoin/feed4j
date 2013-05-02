package it.sauronsoftware.feed4j;

import it.sauronsoftware.feed4j.bean.Feed;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.icu.text.CharsetDetector;

/**
 * The feed parser. It can parse RSS 1.0, RSS 2.0, Atom 0.3 and Atom 1.0.
 * 
 * @author Carlo Pelliccia
 */
public class FeedParser {

	private static Logger logger = LoggerFactory.getLogger(FeedParser.class);

	/**
	 * Gets the feed from an URL and parses it.
	 * 
	 * @param url
	 *            The feed URL.
	 * @return A Feed object containing the information extracted from the feed.
	 * @throws FeedIOException
	 *             I/O error during conetnts retrieving.
	 * @throws FeedXMLParseException
	 *             The document retrieved is not valid XML.
	 * @throws UnsupportedFeedException
	 *             The XML retrieved does not represents a feed whose kind is
	 *             known by the parser.
	 */
	public static Feed parse(URL url) {
		try {
			// Esegue il parsing iniziale del documento XML.
			SAXReader saxReader = new SAXReader(
					new org.ccil.cowan.tagsoup.Parser());
			CharsetDetector charsetDetector = new CharsetDetector();

			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url.toURI());

			try {

				HttpResponse response1 = httpclient.execute(httpGet);
				HttpEntity entity = response1.getEntity();
				InputStream content = entity.getContent();

				logger.info(response1.getStatusLine().toString());

				HttpEntity entity1 = response1.getEntity();

				Header contentEncoding = entity.getContentEncoding();
				String encoding = "UTF-8";

				if (contentEncoding != null) {
					encoding = contentEncoding.getValue();
				}

				Document document = saxReader.read(charsetDetector.getReader(
						new BufferedInputStream(content), encoding));

				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity1);

				// Cerca il modulo di interpretazione del feed.
				int code = FeedRecognizer.recognizeFeed(document);
				switch (code) {
				case FeedRecognizer.RSS_1_0:
					return TypeRSS_1_0.feed(url, document);
				case FeedRecognizer.RSS_2_0:
					return TypeRSS_2_0.feed(url, document);
				case FeedRecognizer.ATOM_0_3:
					return TypeAtom_0_3.feed(url, document);
				case FeedRecognizer.ATOM_1_0:
					return TypeAtom_1_0.feed(url, document);
				default:
					throw new UnsupportedFeedException();
				}

			} finally {
				httpGet.releaseConnection();
			}

		} catch (DocumentException | IOException | URISyntaxException e) {
			throw new FeedXMLParseException(e);
		}
	}

}
