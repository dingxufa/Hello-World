package xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by ding on 2018/3/1.
 */
public class ParsePerson {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PersonHandler personHandler = new PersonHandler();
        PersonHandler2 personHandler2 = new PersonHandler2();
        saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("person.xml"),
                personHandler2);
      //  List<Person> persons = personHandler.getPersons();
        List<Person> persons = personHandler2.getPersons();
        for(Person p : persons){
            System.out.println(p.getName() +"--->"+p.getAge());
        }
    }

}
