package bemToJavaGenerator.contollers;

import bemToJavaGenerator.BlockToTextGenerator;
import bemToJavaGenerator.JSONUtils;
import bemToJavaGenerator.bemEntities.Block;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by def on 02.11.16.
 */

@Path("/bem")
public class BemDescriptionController {

    @POST
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convert(
            @FormParam("bemJson") String bemJson) {
        Map<String, String> result = new HashMap<>();
        try {
            Map<String, Object> blockMap = JSONUtils.OBJECT_MAPPER.readValue(bemJson.getBytes(), HashMap.class);
            Block block = new Block(blockMap);
            BlockToTextGenerator generator = new BlockToTextGenerator(block);
            String description = generator.getDescription();
            result.put("status", "success");
            result.put("data", description);
            return Response.status(200).entity(JSONUtils.getJSONString(result)).build();
        }
        catch (Exception e) {
            result.put("status", "error");
            result.put("data", e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
