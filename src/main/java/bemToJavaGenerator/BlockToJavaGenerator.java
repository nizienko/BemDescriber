package bemToJavaGenerator;

import bemToJavaGenerator.bemEntities.Block;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by def on 02.11.16.
 */
public class BlockToJavaGenerator {
    private String target;
    private Block mainBlock;

    public BlockToJavaGenerator(String target, Block block) {
        this.target = target;
        this.mainBlock = block;
    }

    public void generate() throws IOException {
        createBlockClass(mainBlock);
    }

    private void createBlockClass(Block block) throws IOException {
        TypeSpec.Builder blockClassBuilder = TypeSpec.classBuilder(mainBlock.getName())
                .addModifiers(Modifier.PUBLIC);


        TypeSpec blockClass = blockClassBuilder.build();

        JavaFile javaFile = JavaFile.builder("test.code", blockClass)
                .build();

        javaFile.writeTo(System.out);
    }

}
