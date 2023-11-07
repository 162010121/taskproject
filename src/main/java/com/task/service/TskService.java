package com.task.service;

import java.util.List;

import com.task.dto.TaskDTO;

public interface TskService {
	
	
	public TaskDTO saveTask(long userid,TaskDTO taskDTO);
	
	
      public List<TaskDTO>  getAllTasks();
	
	public TaskDTO getTask(long userid,long taskid);
	
	public void deleteTask(long userid,long taskid);


	
	
}
