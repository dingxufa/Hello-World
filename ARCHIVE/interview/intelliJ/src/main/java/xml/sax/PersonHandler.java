package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2018/3/1.
 */
public class PersonHandler extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    private String tag;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public void startDocument() throws SAXException {
        persons = new ArrayList<Person>();
    }



    @Override
    public void endDocument() throws SAXException {
        System.out.println("文档处理结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("开始一个元素 ：" +qName +" localName="+localName);
        if(null != qName){
            tag = qName;
        }
        if( null!=qName && qName.equals("person")){
            person = new Person();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("person")){
            this.persons.add(person);
        }
        tag=null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length);
        if(null!=tag && tag.equals("name")){
            person.setName(str);
        }else if(null != tag && tag.equals("age")){
            person.setAge(Integer.valueOf(str));
        }
    }
}
