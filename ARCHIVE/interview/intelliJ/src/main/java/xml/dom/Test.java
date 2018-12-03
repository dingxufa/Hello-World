package xml.dom;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ding on 2018/3/5.
 */
public class Test {
    private Map<String, ActionMapping> allActions;

    public Test() {
        this.allActions = new HashMap<String, ActionMapping>();
        this.init2();
        System.out.println(this.getClass().getResourceAsStream("/"));

    }


    public void init(){
        /********SAX读取配置文件***********/
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ParseXMLHandler parseXMLHandler = new ParseXMLHandler();
            System.out.println(this.getClass().getResourceAsStream("/mystruts.xml"));
            parser.parse(this.getClass().getResourceAsStream("/mystruts.xml"),parseXMLHandler);
            allActions = parseXMLHandler.getAllActions();
            System.out.println(allActions);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init2(){
        /********DOM4J读取配置文件***********/
        try{
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(this.getClass().getResourceAsStream("/mystruts.xml"));
            Element root = doc.getRootElement();
            Element ele_package = root.element("package");
            List<Element> listAction = ele_package.elements("action");

            for(Element ele_action:listAction){
                ActionMapping actionMapping = new ActionMapping();
                actionMapping.setName(ele_action.attributeValue("name"));
                actionMapping.setClassName(ele_action.attributeValue("class"));
                actionMapping.setMethod(ele_action.attributeValue("method"));

                Map<String, Result> results = new HashMap<String, Result>();
                Iterator<Element> it = ele_action.elementIterator("result");
                while(it.hasNext()){
                    Element ele_result = it.next();

                    Result res = new Result();
                    res.setName(ele_result.attributeValue("name"));
                    res.setType(ele_result.attributeValue("type"));
                    res.setPage(ele_result.getTextTrim());
                    results.put(res.getName(),res);
                }
                actionMapping.setResults(results);
                allActions.put(actionMapping.getName(), actionMapping);
            }
            System.out.println(allActions);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args){
        new Test().init();

    }
}
