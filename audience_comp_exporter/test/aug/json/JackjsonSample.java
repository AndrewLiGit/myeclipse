
package aug.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class JackjsonSample {
    private static ObjectMapper mapper;
    
    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {   
        if (createNew) {   
            return new ObjectMapper();   
        } else if (mapper == null) {   
            mapper = new ObjectMapper();   
        }   
        return mapper;   
    }   
    
    public static void main(String[] args)
        throws Exception {

        String jsonStr = "{\"dataCollection\":[{\"name\":\"Marijuana Autoflow\",\"id\":40156446,\"type\":\"component\",\"shared\":false,\"description\":\"\",\"version\":1,\"status\":0,\"groupname\":\"7NEWS\",\"author\":\"\",\"articleid\":\"40156445\",\"urltitle\":\"https-scripps-cms-endplay-com-group-control_panel-manage-p_p_id-components-p_p_lifecycle-0-p_p_state-maximized-p_p_mode-view-doasgroupid-10950-ref-145\",\"thumbnailUrl\":\"\",\"thumbnailCount\":null,\"groupid\":10950,\"structureid\":\"AUTOFLOW\",\"templateid\":\"\",\"categoryid\":null,\"categoryname\":\"\",\"statusLabel\":\"Published\",\"sharingstatus\":0,\"createdate\":\"20121116232938969\",\"modifieddate\":\"20121116232939714\",\"statusdate\":\"20121116232939714\",\"createdby\":386513,\"createdateformatted\":\"11/16/2012\",\"modifieddateformatted\":\"11/16/2012\",\"statusdateformatted\":\"11/16/2012\",\"selected\":true,\"ordinal\":0},{\"name\":\"Odds-against-him-Obama-still-bets-on-big-deal_49502073\",\"id\":49063171,\"type\":\"feed-story\",\"shared\":false,\"description\":\" WASHINGTON (AP) — President Barack Obama says he once again wants to seek a big fiscal deal that would raise taxes and trim billions from expensive and ever growing entitlement programs. But with automatic federal spending cuts ready to start taking effect, the path toward that grand bargain has significantly narrowed. \",\"version\":1,\"groupname\":\"7NEWS\",\"status\":0,\"author\":\"\",\"articleid\":\"49063170\",\"urltitle\":\"odds-against-him-obama-still-bets-on-big-deal_49502073\",\"thumbnailUrl\":\"\",\"thumbnailCount\":null,\"groupid\":10950,\"structureid\":\"STORY\",\"templateid\":\"STORY\",\"categoryid\":17430,\"categoryname\":\"Business News\",\"statusLabel\":\"Published\",\"sharingstatus\":0,\"createdate\":\"20130304073606921\",\"modifieddate\":\"20130304073607239\",\"statusdate\":\"20130304073607239\",\"createdby\":10199,\"createdateformatted\":\"03/04/2013\",\"modifieddateformatted\":\"03/04/2013\",\"statusdateformatted\":\"03/04/2013\",\"articleUrl\":null,\"selected\":true,\"ordinal\":1},{\"name\":\"Fuicelli and Lee\",\"id\":41116878,\"type\":\"component\",\"shared\":false,\"description\":\"\",\"version\":1,\"groupname\":\"7NEWS\",\"status\":0,\"author\":\"\",\"articleid\":\"41116877\",\"urltitle\":\"https-scripps-cms-endplay-com-group-control_panel-manage-p_p_id-components-p_p_lifecycle-0-p_p_state-maximized-p_p_mode-view-doasgroupid-10950-ref-149\",\"thumbnailUrl\":\"\",\"thumbnailCount\":null,\"groupid\":10950,\"structureid\":\"LINK\",\"templateid\":\"\",\"categoryid\":null,\"categoryname\":\"\",\"statusLabel\":\"Published\",\"sharingstatus\":0,\"createdate\":\"20121119212808390\",\"modifieddate\":\"20121119212809157\",\"statusdate\":\"20121119212809157\",\"createdby\":386049,\"createdateformatted\":\"11/19/2012\",\"modifieddateformatted\":\"11/19/2012\",\"statusdateformatted\":\"11/19/2012\",\"articleUrl\":null,\"selected\":true,\"ordinal\":2},{\"name\":\"Lady-Gaga-visits-abused-girls-on-Thanksgiving_70972911\",\"id\":42934699,\"type\":\"feed-story\",\"shared\":false,\"description\":\" LADY GAGA marked America&#039;s Thanksgiving Day on Thursday (22Nov12) by visiting a center for sexually abused girls in Peru. \",\"version\":1,\"groupname\":\"7NEWS\",\"status\":0,\"author\":\"\",\"articleid\":\"42934698\",\"urltitle\":\"lady-gaga-visits-abused-girls-on-thanksgiving_70972911\",\"thumbnailUrl\":\"\",\"thumbnailCount\":null,\"groupid\":10950,\"structureid\":\"STORY\",\"templateid\":\"STORY\",\"categoryid\":17364,\"categoryname\":\"Celebrity\",\"statusLabel\":\"Published\",\"sharingstatus\":0,\"createdate\":\"2012112513410300\",\"modifieddate\":\"2012112602182715\",\"statusdate\":\"2012112602182715\",\"createdby\":10199,\"createdateformatted\":\"11/25/2012\",\"modifieddateformatted\":\"11/25/2012\",\"statusdateformatted\":\"11/25/2012\",\"articleUrl\":null,\"selected\":true,\"ordinal\":3}]}";

        Object[] obj = readValueFromJson(jsonStr, "dataCollection:name").toArray();
        System.out.println(Arrays.toString(obj));
    }

    public static List<String> readValueFromJson(String json, String tagPath)
        throws Exception {

        List<String> value = new ArrayList<String>();
        if (isEmpty(json) || (isEmpty(tagPath))) {
            return value;
        }
        ObjectMapper mapper = getMapperInstance(false);
        String[] path = tagPath.split(":");
        JsonNode node = mapper.readTree(json);
        getJsonValue(node, path, value, 1);
        return value;
    }

    public static void getJsonValue(JsonNode node, String[] path, List<String> values, int nextIndex) {
        if (isEmpty(node)) {
            return;
        }
        if (nextIndex == path.length) {
            if (node.isArray()) {
                for (int i = 0; i < node.size(); i++) {
                    JsonNode child = node.get(i).get(path[nextIndex - 1]);
                    if (isEmpty(child)) {
                        continue;
                    }
                    values.add(child.toString());
                }
            }
            else {
                JsonNode child = node.get(path[nextIndex - 1]);
                if (!isEmpty(child)) {
                    values.add(child.toString());
                }
            }
            return;
        }
        node = node.get(path[nextIndex - 1]);
        if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                getJsonValue(node.get(i), path, values, nextIndex + 1);
            }
        }
        else {
            getJsonValue(node, path, values, nextIndex + 1);
        }
    }

    public static boolean isEmpty(Object obj) {
        boolean result = true;
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
        }
        else if (obj instanceof Collection) {
            result = ((Collection) obj).size() == 0;
        }
        else {
            result = ((obj == null) || (obj.toString().trim().length() < 1)) ? true : false;
        }
        return result;
    }

}
