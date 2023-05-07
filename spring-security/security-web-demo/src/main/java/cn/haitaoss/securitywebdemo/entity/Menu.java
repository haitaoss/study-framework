package cn.haitaoss.securitywebdemo.entity;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-22 12:20
 *
 */
public class Menu {
    private Long id;
    private String name;
    private String url;
    private Long parentId;
    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
