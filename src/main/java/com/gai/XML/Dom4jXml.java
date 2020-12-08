package com.gai.XML;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Dom4jXml {
    public static void main(String[] args) throws DocumentException {
        String path = Dom4jXml.class.getClassLoader().getResource("stu.xml").getPath();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(path));
        //拿到根节点
        Element element = document.getRootElement();
        getNodes(element);
    }

    public static void getNodes(Element element){
        System.out.println("当前节点名称:" + element.getName());
        // 获取属性ID
        List<Attribute> attributes = element.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性:" + attribute.getName() + "---" + attribute.getText());
        }
        if (!element.getTextTrim().equals("")) {
            System.out.println(element.getName() + "--" + element.getText());
        }
        // 使用迭代器遍历
        Iterator<Element> elementIterator = element.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            getNodes(next);
        }
    }
}
