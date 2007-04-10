/**
 *
 */
package org.jaden.websterm.lib.function;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

/**
 * @author jack
 *
 */
public class FunctionRegistrationEngine {
	private Document registrationDocument;

	private String documentPath;

	public FunctionRegistrationEngine(ClassPathResource resource) {
		SAXReader parser = new SAXReader();
		try {
			documentPath = resource.getFile().getAbsolutePath();
			registrationDocument = parser.read(resource.getInputStream());
		} catch (DocumentException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void registerFunction(String description) {
		Document document = null;

		try {
			document = DocumentHelper.parseText(description);
		} catch (DocumentException exception) {
			exception.printStackTrace();
		}

		registrationDocument.getRootElement().add(document.getRootElement());

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(documentPath),
					format);
			writer.write(registrationDocument);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @return the documentPath
	 */
	public String getDocumentPath() {
		return documentPath;
	}

	/**
	 * @param documentPath
	 *            the documentPath to set
	 */
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	/**
	 * @return the registrationDocument
	 */
	public Document getRegistrationDocument() {
		return registrationDocument;
	}

	/**
	 * @param registrationDocument
	 *            the registrationDocument to set
	 */
	public void setRegistrationDocument(Document registrationDocument) {
		this.registrationDocument = registrationDocument;
	}
}
