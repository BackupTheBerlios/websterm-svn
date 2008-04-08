/*
 * RTEDataIterator.java 1.00 Thu Feb 21 23:40:44 +0000 2008
 *
 */

package org.jaden.rte;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.tokenizer.RegExTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class RTEDataIterator implements Iterator<THPair>, Iterable<THPair> {
    public static final String TOKENIZE_REGEX = "$?[0-9]+\\.[0-9]+|[a-zA-Z0-9'$]+";

    private final TokenizerFactory factory = new RegExTokenizerFactory(
            TOKENIZE_REGEX);

    private Element root;

    private Iterator<Element> iterator;

    public RTEDataIterator(InputStream in) {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(in);
            root = doc.getRootElement();
            iterator = root.elementIterator("pair");
        } catch(DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public THPair next() {
        Element elem = iterator.next();
        String entailmentStr = elem.attributeValue("entailment");
        boolean entailment = false;
        if("YES".equals(entailmentStr))
            entailment = true;
        String length = elem.attributeValue("length");
        int id = Integer.parseInt(elem.attributeValue("id"));

        Element textElem = elem.element("t");
        String text = textElem.getText();
        List<String> textList = buildTextList(text);

        Element hypElem = elem.element("h");
        String hypothesis = hypElem.getText();
        List<String> hypList = buildTextList(hypothesis);

        THPair pair = new THPair(id, textList, hypList,
                entailment, length);
        
        return pair;
    }

    @Override
    public void remove() {

    }

    public void reset() {
        iterator = root.elementIterator("pair");
    }

    public Iterator<THPair> iterator() {
        return this;
    }

    private List<String> buildTextList(String text) {
        char[] chars = text.toCharArray();
        Tokenizer tokenizer = factory.tokenizer(chars, 0, chars.length);
        List<String> textList = new ArrayList<String>();
        List<String> whiteSpaces = new ArrayList<String>();
        tokenizer.tokenize(textList, whiteSpaces);
        return textList;
    }

    public static void main(String args[]) throws Exception {
        RTEDataIterator iterator = new RTEDataIterator(
                new FileInputStream("data/rte3_dev.xml"));
        for(THPair pair : iterator)
            System.out.println(pair);
    }
}

