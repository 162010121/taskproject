package com.task.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.dto.TaskDTO;
import com.task.entity.Task;
import com.task.entity.Users;
import com.task.exception.APINotFound;
import com.task.exception.TaskNotFound;
import com.task.exception.UserNotFound;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;
import com.task.service.TskService;

@Service
public class TaskServiceImpl implements TskService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDTO saveTask(long userid, TaskDTO taskDTO) {
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d Not found ", userid)));
		Task task = new Task();
		task.setUser(user);
		BeanUtils.copyProperties(taskDTO, task);
		taskRepository.save(task);
		return taskDTO;
	}

	@Override
	public TaskDTO getTask(long userid, long taskid) {
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d Not found ", userid)));
		Task task=taskRepository.findById(taskid).
				orElseThrow(()-> new TaskNotFound(String.format("Task Id %d Not found", taskid)));
		
		if (user.getId() !=task.getUser().getId()) {
			
			throw new APINotFound(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
			
		}
		
		return mapper.map(task, TaskDTO.class);
	}

	@Override
	public void deleteTask(long userid, long taskid) {
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d Not found ", userid)));
		Task task=taskRepository.findById(taskid).
				orElseThrow(()-> new TaskNotFound(String.format("Task Id %d Not found", taskid)));
		taskRepository.deleteById(taskid);
if (user.getId() !=task.getUser().getId()) {
			
			throw new APINotFound(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
			
		}
		
	}

	@Override
	public List<TaskDTO> getAllTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	

	



	

	
		
	}


