package com.task.service;

import java.util.List;

import com.task.dto.TaskDTO;
import com.task.entity.Task;

public interface TskService {
	
	
	public TaskDTO saveTask(long userid,TaskDTO taskDTO);
	
	
	//public List<TaskDTO>  getAllTasks(long userid);
	
	public TaskDTO getTask(long userid,long taskid);
	
	public void deleteTask(long userid,long taskid);


	
	
}
