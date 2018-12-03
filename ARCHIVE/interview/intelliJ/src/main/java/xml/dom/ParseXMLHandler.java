package xml.dom;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <action name="login" class="cn.itcast.framework.action.LoginAction" method="login">
     <result name="success" type="redirect">/index.jsp</result>
     <result name="loginFaild">/login.jsp</result>
    </action>
 */
public class ParseXMLHandler extends DefaultHandler {
    private Map<String, ActionMapping> allActions ;
    private ActionMapping actionMapping;
    private Result result;
    private String tag;

    public Map<String, ActionMapping> getAllActions() {
        return allActions;
    }

    public void setAllActions(Map<String, ActionMapping> allActions) {
        this.allActions = allActions;
    }


    @Override
    public void startDocument() throws SAXException {
        System.out.println("=====解析开始");
        allActions = new HashMap<String, ActionMapping>();
        tag="";
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("====解析结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("action".equals(qName)) {
            actionMapping = new ActionMapping();
            actionMapping.setResults(new HashMap<String, Result>());
            actionMapping.setName(attributes.getValue("name"));
            actionMapping.setClassName(attributes.getValue("class"));
            actionMapping.setMethod(attributes.getValue("method"));

        }else if("result".equals(qName)){
            result = new Result();
            result.setName(attributes.getValue("name"));
            result.setType(attributes.getValue("type"));
        }
		/*System.out.println("===========name");
		System.out.println(attributes.getValue("name"));
		System.out.println(attributes.getValue("class"));
		System.out.println(attributes.getValue("method"));
		System.out.println("=====index");
		System.out.println(attributes.getValue(0));
		System.out.println(attributes.getValue(1));
		System.out.println(attributes.getValue(2));*/
        tag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("result".equals(qName)) {
            actionMapping.getResults().put(result.getName(), result);
        }
        if("action".equals(qName)){
            allActions.put(actionMapping.getName(), actionMapping);
        }
        tag ="";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length);
        if("result".equals(tag)){
            result.setPage(str);
        }
        System.out.println("--"+str);
    }
}
