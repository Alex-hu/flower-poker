package com.bolegame.flowerpoker.common;

import com.bolegame.flowerpoker.entity.Player;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 游戏大厅基类.
 * @author: Hu Zhongyuan
 * @version: 13-11-6
 */
public class BaseHall {
    protected int hallId;
    protected String hallName;
    protected int tableUserTotal = 6;
    protected Map<String, Long[]> tableMap = new HashMap<String, Long[]>(MAX_TABLE_COUNT);
    protected Map<Long, Player> players;

    public static final int MAX_HALL_COUNT = 5;
    public static final int MAX_TABLE_COUNT = 50;
    public static final int MAX_PLAYER_COUNT = 200;

    /**
     * 初始化TableMap.
     */
    public void initTableMap(){
        this.tableMap.clear();
        for (int tableId = 0; tableId < MAX_TABLE_COUNT; tableId ++){
            Long[] playerIds = new Long[this.tableUserTotal];
            this.tableMap.put(Integer.toString(tableId), playerIds);
        }
    }

    /**
     * 根据Id获得玩家
     * @param playId
     * @return
     */
    public Player getPlayer(Long playId){
        return players.get(playId);
    }

    /**
     * 检查table是否已经满员.
     * @param tableId
     * @return
     */
    public boolean checkTableFullUser(String tableId){
        Long[] playerIds = this.tableMap.get(tableId);
        for (Long playerId : playerIds){
            if (playerId == null || playerId < 0){
                return false;
            }
        }
        return true;
    }

    public String removePlayFromTable(Long playId){
         Player player = players.get(playId);
        String tableId = player.getTableId();
        if (player != null && StringUtils.isNotEmpty(tableId)){
            Long[] playIds = tableMap.get(tableId);
            playIds[player.getPosition().intValue()] = Long.valueOf(0);
            player.freeInHall();
        }
        return tableId;
    }


    public BaseHall() {
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getTableUserTotal() {
        return tableUserTotal;
    }

    public void setTableUserTotal(int tableUserTotal) {
        this.tableUserTotal = tableUserTotal;
    }

    public Map<String, Long[]> getTableMap() {
        return tableMap;
    }

    public void setTableMap(Map<String, Long[]> tableMap) {
        this.tableMap = tableMap;
    }

    public Map<Long,Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Long,Player> players) {
        this.players = players;
    }
}
