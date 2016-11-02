package bemToJavaGenerator.bemEntities;

import bemToJavaGenerator.LocatorUtils;
import com.google.common.base.CaseFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by def on 02.11.16.
 */
public class Element implements IBlock{
    private String name;
    private String locator;
    private String elementClazz;
    private List<Block> innerBlocks = new ArrayList<Block>();
    private List<Element> innerElements =  new ArrayList<Element>();
    private String contentText;

    public Element(Map elementMap, Block parentBlock) {
        this.name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, elementMap.get("elem").toString()) + "Block";
        this.elementClazz = String.format("%s__%s", parentBlock.getBlockClazz(), elementMap.get("elem").toString());
        List<Map<String, Object>> content = null;

        try {
            content = (List<Map<String, Object>>) elementMap.get("content");
        }
        catch (ClassCastException e) {
            if (elementMap.get("content").getClass() == String.class) {
                contentText = (String) elementMap.get("content");
            }
            else {
                content = new ArrayList<>();
                Map<String, Object> contentMap = (Map<String, Object>) elementMap.get("content");
                content.add(contentMap);
            }
        }
        if (content != null) {
            content.forEach((child) -> {
                if (child.containsKey("block")) {
                    try {
                        innerBlocks.add(new Block(child));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (child.containsKey("elem")) {
                    innerElements.add(new Element(child, parentBlock));
                }
            });
        }
    }

    public String getName() {
        return name;
    }

    public String getLocator() {
        return LocatorUtils.getLocatorByClass(elementClazz);
    }

    public String getElementClazz() {
        return elementClazz;
    }

    public List<Block> getInnerBlocks() {
        return innerBlocks;
    }

    public List<Element> getInnerElements() {
        return innerElements;
    }

    public String getContentText() {
        return contentText;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", locator='" + locator + '\'' +
                ", elementClazz='" + elementClazz + '\'' +
                ", innerBlocks=" + innerBlocks +
                ", innerElements=" + innerElements +
                ", contentText='" + contentText + '\'' +
                '}';
    }
}
