package com.spring.dao;

import java.util.List;
import java.util.Map;

import com.spring.model.Sc;

public interface ScMapper {
	public List<Sc> selectBysnocno(String sno, String cno);

	public int Total(String sno, String cno);

	public List<Sc> getscFromsnocnoByPage(Map<String, Object> map);

	public String selectAllbykey(Map<String, Object> map);

	public int updatebykey(Sc sc);

	public int insertSelective(Sc sc);

	public int totalBysclasstimes(Map<String, Object> map);

	public List<Sc> getscFromsclasstimesByPage(Map<String, Object> map);

	public int TotalByName(String sname, String cno);

	public List<Sc> getscFromsnamecnoByPage(Map<String, Object> map);

	public Sc selectByscid(int sc_id);

	public int downloaded(int sc_id);

	public List<Sc> getScByMajorClasTimes(Map<String, Object> map);

	public List<Sc> getScUnByMajorClasTimes(Map<String, Object> map);

	public List<Sc> getAllBySno(String sno);

	public int deleteByScid(int sc_id);
}