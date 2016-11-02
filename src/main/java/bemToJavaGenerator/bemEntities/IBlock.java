package bemToJavaGenerator.bemEntities;

import java.util.List;

/**
 * Created by def on 02.11.16.
 */
public interface IBlock {
    String getName();
    List<Block>getInnerBlocks();
    List<Element>getInnerElements();
    String getLocator();
}
