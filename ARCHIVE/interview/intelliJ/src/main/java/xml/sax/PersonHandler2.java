package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2018/3/2.
 */
public class PersonHandler2 extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    private String tag;

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("===========开始解析文档===========");
        persons = new ArrayList<Person>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("===========解析文档结束==========");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("person".equals(qName)){
            person = new Person();
        }
        tag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("person".equals(qName)){
            persons.add(person);
        }
        tag="";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length);
        if("name".equals(tag)){
            person.setName(str);
        }else if("age".equals(tag)){
            person.setAge(Integer.valueOf(str));
        }
    }
}
