package com.spring.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.TaskMapper;
import com.spring.model.Task;
import com.spring.service.TaskService;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

	@Resource
	public TaskMapper taskMapper = null;

	@Override
	public List<Task> getAll(String cno) {
		return this.taskMapper.getAll(cno);
	}

	@Override
	public Task selectBycnosctimes(Map<String, Object> map) {
		return this.taskMapper.selectBycnosctimes(map);
	}

	@Override
	public String selectsctimeMax(String cno) {
		return this.taskMapper.selectsctimeMax(cno);
	}

	@Override
	public int insertAll(Map<String, Object> map) {
		return this.taskMapper.insertAll(map);
	}

	@Override
	public int updateBykey(Map<String, Object> map) {
		return this.taskMapper.updateBykey(map);
	}

	@Override
	public int insertAllFile(Map<String, Object> map) {
		return this.taskMapper.insertAllFile(map);
	}

	@Override
	public int updateFileBykey(Map<String, Object> map) {
		return this.taskMapper.updateFileBykey(map);
	}

}
