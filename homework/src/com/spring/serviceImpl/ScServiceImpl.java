package com.spring.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.ScMapper;
import com.spring.model.Sc;
import com.spring.model.ScData;
import com.spring.service.ScService;

@Service("scService")
public class ScServiceImpl implements ScService {

	@Resource
	public ScMapper scMapper = null;

	@Override
	public List<Sc> selectBysnocno(String sno, String cno) {
		return this.scMapper.selectBysnocno(sno, cno);
	}

	@Override
	public int Total(String sno, String cno) {
		return this.scMapper.Total(sno, cno);
	}

	@Override
	public List<Sc> getscFromsnocnoByPage(Map<String, Object> map) {
		return this.scMapper.getscFromsnocnoByPage(map);
	}

	@Override
	public String selectAllbykey(Map<String, Object> map) {
		return this.scMapper.selectAllbykey(map);
	}

	@Override
	public int updatebykey(Sc sc) {
		return this.scMapper.updatebykey(sc);
	}

	@Override
	public int insertSelective(Sc sc) {
		return this.scMapper.insertSelective(sc);
	}

	@Override
	public int totalBysclasstimes(Map<String, Object> map) {
		return this.scMapper.totalBysclasstimes(map);
	}

	@Override
	public List<Sc> getscFromsclasstimesByPage(Map<String, Object> map) {
		return this.scMapper.getscFromsclasstimesByPage(map);
	}

	@Override
	public int TotalByName(String sname, String cno) {
		return this.scMapper.TotalByName(sname, cno);
	}

	@Override
	public List<Sc> getscFromsnamecnoByPage(Map<String, Object> map) {
		return this.scMapper.getscFromsnamecnoByPage(map);
	}

	@Override
	public Sc selectByscid(int sc_id) {
		return this.scMapper.selectByscid(sc_id);
	}

	@Override
	public int downloaded(int sc_id) {
		return this.scMapper.downloaded(sc_id);
	}

	@Override
	public List<Sc> getScByMajorClasTimes(Map<String, Object> map) {
		return this.scMapper.getScByMajorClasTimes(map);
	}

	@Override
	public List<Sc> getScUnByMajorClasTimes(Map<String, Object> map) {
		return this.scMapper.getScUnByMajorClasTimes(map);
	}

	@Override
	public List<Sc> getAllBySno(String sno) {
		return this.scMapper.getAllBySno(sno);
	}

	@Override
	public int deleteByScid(int sc_id) {
		return this.scMapper.deleteByScid(sc_id);
	}

}
