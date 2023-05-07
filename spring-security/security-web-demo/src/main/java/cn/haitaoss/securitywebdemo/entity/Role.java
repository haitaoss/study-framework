package cn.haitaoss.securitywebdemo.entity;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-22 12:20
 *
 */
public class Role {
    private Long id;
    private String name;

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
}