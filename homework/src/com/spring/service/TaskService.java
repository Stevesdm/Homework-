package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.model.Task;

public interface TaskService {
	List<Task> getAll(String cno);

	Task selectBycnosctimes(Map<String, Object> map);

	String selectsctimeMax(String cno);

	int insertAll(Map<String, Object> map);

	int updateBykey(Map<String, Object> map);

	int insertAllFile(Map<String, Object> map);

	int updateFileBykey(Map<String, Object> map);
}
