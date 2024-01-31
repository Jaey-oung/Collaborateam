package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeamDaoImpl implements TeamDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.TeamMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    public List<TeamDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    public void removeAll() throws Exception {
        session.delete(namespace+"removeAll");
    }

    public int insert(TeamDto teamDto) throws Exception {
        return session.insert(namespace+"insert", teamDto);
    }

    public TeamDto select(Integer tno) throws Exception {
        return session.selectOne(namespace+"select", tno);
    }

    public int modify(TeamDto teamDto) throws Exception {
        return session.update(namespace+"modify", teamDto);
    }

    public int remove(Integer tno, String leader) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tno", tno);
        map.put("leader", leader);
        return session.delete(namespace+"remove", map);
    }

    @Override
    public List<TeamDto> teamPage(String id, TeamListCondition tlc) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("tlc", tlc);
        return session.selectList(namespace+"teamPage", map);
    }

    @Override
    public int teamCnt(String id) throws Exception {
        return session.selectOne(namespace+"teamCnt", id);
    }

    @Override
    public List<TeamDto> selectLeaderTeam(String id) throws Exception {
        return session.selectList(namespace+"selectLeaderTeam", id);
    }
}