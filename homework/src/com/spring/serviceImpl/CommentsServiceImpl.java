package com.spring.serviceImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.CommentMapper;
import com.spring.model.Comment;
import com.spring.service.CommentService;

@Service("commentsService")
public class CommentsServiceImpl implements CommentService {
	
	@Resource
	public CommentMapper commentsMapper = null;


}
