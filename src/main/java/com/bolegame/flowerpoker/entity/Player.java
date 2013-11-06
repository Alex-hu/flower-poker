package com.bolegame.flowerpoker.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Hu Zhongyuan
 * Date: 13-11-6
 * Time: 下午9:25
 */
public class Player implements Serializable{
    /**
     * 玩家id.
     */
    private Long playerId;
    /**
     * 大厅id.
     */
    private Integer hallId;
    /**
     * 游戏桌id.
     */
    private String tableId;
    /**
     * 在游戏桌中的位置.
     */
    private Integer position = new Integer(-1);
    /**
     * 姓名.
     */
    private String name;
    /**
     * 密码.
     */
    private String password;
    /**
     * 头像图标位置.
     */
    private String headIconPath;
    /**
     * 性别.
     */
    private Boolean sex;

    public void freeInHall(){
        this.tableId = "";
        this.position = 0;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadIconPath() {
        return headIconPath;
    }

    public void setHeadIconPath(String headIconPath) {
        this.headIconPath = headIconPath;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
