package cuner;

import com.alibaba.fastjson.JSONObject;
import cuner.common.util.JsonUtil;
import cuner.common.util.MongoUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void insert()
    {
        JSONObject jobj = null;
        for (int i=11; i<20; i++) {
            jobj = new JSONObject();
            jobj.put("name", "test" + i);
            MongoUtil.insert("test", JsonUtil.toJSONString(jobj));
        }
    }

    @Test
    public void update()
    {
        JSONObject jobj = null;
        JSONObject where = new JSONObject();
        where.put("name", "test19");
        jobj = new JSONObject();
        jobj.put("name", "test19u");
        MongoUtil.update("test", JsonUtil.toJSONString(where), JsonUtil.toJSONString(jobj));
    }

    @Test
    public void query()
    {
        JSONObject where = new JSONObject();
        where.put("name", "test11");
        List<Map<String, Object>> list = MongoUtil.queryList("test", JsonUtil.toJSONString(where));
        list.forEach(item->{
            System.out.println(item.get("name"));
        });
    }

    @Test
    public void delete()
    {
        JSONObject where = new JSONObject();
        where.put("name", "test11");
        long rev = MongoUtil.delete("test", JsonUtil.toJSONString(where));
        assertTrue(rev>0);
    }
}
