package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TeamDto;
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

    public void deleteAll() throws Exception {
        session.delete(namespace+"deleteAll");
    }

    public int insert(TeamDto teamDto) throws Exception {
        return session.insert(namespace+"insert", teamDto);
    }   // Create - C

    public TeamDto select(Integer tno) throws Exception {
        return session.selectOne(namespace+"select", tno);
    }   // Read - R

    public int update(TeamDto teamDto) throws Exception {
        return session.update(namespace+"update", teamDto);
    }   // Update - U

    public int delete(Integer tno, String leader) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tno", tno);
        map.put("leader", leader);
        return session.delete(namespace+"delete", map);
    }   // Delete - D
}