package bemToJavaGenerator;

import bemToJavaGenerator.bemEntities.Block;
import bemToJavaGenerator.bemEntities.IBlock;

/**
 * Created by def on 02.11.16.
 */
public class BlockToTextGenerator {
    private Block block;
    public BlockToTextGenerator(Block block) {
        this.block = block;
    }

    public void print() {
        printBlockInfo(block);
    }

    public String getDescription() {
        StringBuilder result = new StringBuilder();
        describeInHtml(block, result);
        return result.toString();
    }

    private void printBlockInfo(IBlock block){
        StringBuilder blockInfo = new StringBuilder();
        blockInfo.append(block.getName())
                .append("\n")
                .append("locator: \"").append(block.getLocator()).append("\"\n")
                .append("Fields: ");
        block.getInnerBlocks().forEach((b -> blockInfo.append(b.getName()).append(" ")));
        block.getInnerElements().forEach((b -> blockInfo.append(b.getName()).append(" ")));
        blockInfo.append("\n-----------------------------");
        System.out.println(blockInfo.toString());
        block.getInnerBlocks().forEach(this::printBlockInfo);
        block.getInnerElements().forEach(this::printBlockInfo);

    }

    private void describeInHtml(IBlock block, StringBuilder blockInfo){
        blockInfo.append(block.getName())
                .append("<br>")
                .append("locator: \"").append(block.getLocator()).append("\"<br>")
                .append("Fields: ");
        block.getInnerBlocks().forEach((b -> blockInfo.append(b.getName()).append(" ")));
        block.getInnerElements().forEach((b -> blockInfo.append(b.getName()).append(" ")));
        blockInfo.append("<br>-----------------------------");
        System.out.println(blockInfo.toString());
        block.getInnerBlocks().forEach((b) -> describeInHtml(b, blockInfo));
        block.getInnerElements().forEach(b -> describeInHtml(b, blockInfo));

    }
}
