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
public class Block implements IBlock{
    private String name;
    private String blockClazz;
    private String elementClazz;
    private List<Block> innerBlocks = new ArrayList<Block>();
    private List<Element> innerElements =  new ArrayList<Element>();
    private String contentText;

    public Block(Map blockMap) throws IOException {

        this.name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, blockMap.get("block").toString()) + "Block";
        this.blockClazz = blockMap.get("block").toString();
        if (blockMap.containsKey("elem")) {
            elementClazz = String.format("%s__%s", blockClazz, blockMap.get("elem"));
        }

        List<Map<String, Object>> content = null;

        try {
            content = (List<Map<String, Object>>) blockMap.get("content");
        }
        catch (ClassCastException e) {
            if (blockMap.get("content").getClass() == String.class) {
                contentText = (String) blockMap.get("content");
            }
            else {
                content = new ArrayList<>();
                Map<String, Object> contentMap = (Map<String, Object>) blockMap.get("content");
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
                    innerElements.add(new Element(child, this));
                }
            });
        }

    }


    @Override
    public String toString() {
        return "Block{" +
                "name='" + name + '\'' +
                ", blockClazz='" + blockClazz + '\'' +
                ", elementClazz='" + elementClazz + '\'' +
                ", innerBlocks=" + innerBlocks +
                ", innerElements=" + innerElements +
                ", contentText='" + contentText + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLocator() {
        return LocatorUtils.getLocatorByClass(blockClazz);
    }

    public String getBlockClazz() {
        return blockClazz;
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
}
