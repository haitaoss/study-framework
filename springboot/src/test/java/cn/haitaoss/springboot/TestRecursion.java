package cn.haitaoss.springboot;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-09 13:41
 *
 */
public class TestRecursion {

    @Test
    public void test() {

        // 数据库获取的菜单数据
        List<TreeData> treeData4Db = new ArrayList<>();
        treeData4Db.add(new TreeData(0, null, "根节点"));
        treeData4Db.add(new TreeData(1, 0, "菜单1"));
        treeData4Db.add(new TreeData(2, 0, "菜单2"));
        treeData4Db.add(new TreeData(3, 0, "菜单3"));
        treeData4Db.add(new TreeData(4, 1, "菜单1-1"));
        treeData4Db.add(new TreeData(5, 1, "菜单1-2"));
        treeData4Db.add(new TreeData(6, 1, "菜单1-3"));
        treeData4Db.add(new TreeData(7, 4, "菜单1-1-1"));
        treeData4Db.add(new TreeData(8, 4, "菜单1-1-2"));

        TreeData node = treeData4Db
                .stream()
                .filter(item -> 0 == item.getId())
                .findFirst()
                .get();

        node.setChildList(generateTreeDate(node, treeData4Db));
        System.out.println(JSONObject.toJSONString(node));
        // System.out.println("node = " + node);
    }

    private List<TreeData> generateTreeDate(TreeData node, List<TreeData> treeData4Db) {
        return treeData4Db
                .stream()
                .filter(item -> node.id.equals((item.parentId))) // 找到子节点
                .peek(item -> {
                    List<TreeData> treeData = generateTreeDate(item, treeData4Db);
                    item.setChildList(treeData); // 设置子节点数据
                })
                .collect(Collectors.toList());

    }
}

class TreeData {
    Integer id;
    Integer parentId;
    String name;
    List<TreeData> childList;

    public TreeData(Integer id, Integer parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeData> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeData> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "TreeData{" + "id=" + id + ", parentId=" + parentId + ", name='" + name + '\'' + ", childList=" + childList + '}';
    }
}
