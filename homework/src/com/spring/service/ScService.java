package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.model.Sc;
import com.spring.model.ScData;

public interface ScService {
	List<Sc> selectBysnocno(String sno, String cno);

	int Total(String sno, String cno);

	List<Sc> getscFromsnocnoByPage(Map<String, Object> map);

	String selectAllbykey(Map<String, Object> map);

	int updatebykey(Sc sc);

	int insertSelective(Sc sc);

	int totalBysclasstimes(Map<String, Object> map);

	List<Sc> getscFromsclasstimesByPage(Map<String, Object> map);

	int TotalByName(String sname, String cno);

	List<Sc> getscFromsnamecnoByPage(Map<String, Object> map);

	Sc selectByscid(int sc_id);

	int downloaded(int sc_id);

	List<Sc> getScByMajorClasTimes(Map<String, Object> map);

	List<Sc> getScUnByMajorClasTimes(Map<String, Object> map);

	List<Sc> getAllBySno(String sno);

	int deleteByScid(int sc_id);

}
