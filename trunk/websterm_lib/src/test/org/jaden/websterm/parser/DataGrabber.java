/**
 *
 */
package org.jaden.websterm.parser;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author jack
 *
 */
public class DataGrabber {
	public static void main(String args[]) {
		try {
			HttpMethod method = new GetMethod(
					"http://forum.ubuntu.org.cn/viewforum.php?f=48");
			InputStreamReader stream = getUrl(method);

			Element root = getRoot(stream);
			method.releaseConnection();

			NodeList hrefs = root.getElementsByTagName("a");
			for (int i = 0; i < 50; i++) {
				Element href = (Element) hrefs.item(i);
				if (href.getAttribute("class").trim().equals("topictitle")) {
					String url = href.getAttribute("href");
					System.out.println(url);

					method = new GetMethod("http://forum.ubuntu.org.cn/" + url);
					InputStreamReader input = getUrl(method);
					Element tmpRoot = getRoot(input);
					method.releaseConnection();
					System.out.println("here now");

					NodeList posts = tmpRoot.getElementsByTagName("span");
					for (int j = 0; j < posts.getLength(); j++) {
						Element post = (Element) posts.item(j);
						if (post.getAttribute("class").equals("postbody")) {
							System.out.println(post.getTextContent());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Element getRoot(InputStreamReader stream) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(stream));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return parser.getDocument().getDocumentElement();
	}

	private static InputStreamReader getUrl(HttpMethod method)
			throws IOException {
		HttpClient client = new HttpClient();

		InputStreamReader stream = null;
		try {
			int status = client.executeMethod(method);

			if (status != HttpStatus.SC_OK) {
				throw new HttpException("Method failed "
						+ method.getStatusLine().toString());
			}

			stream = new InputStreamReader(method.getResponseBodyAsStream());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stream;
	}
}
