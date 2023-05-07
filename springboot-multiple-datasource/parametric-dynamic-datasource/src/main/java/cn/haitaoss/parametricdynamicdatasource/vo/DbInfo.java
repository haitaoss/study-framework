package cn.haitaoss.parametricdynamicdatasource.vo;

import lombok.Data;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 19:03
 *
 */
@Data
public class DbInfo {
    private String ip;
    private String port;
    private String dbName;
    private String driveClassName;
    private String username;
    private String password;
}