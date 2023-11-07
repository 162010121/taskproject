package com.task.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.TaskDTO;
import com.task.entity.Task;
import com.task.service.TskService;

@RestController
@RequestMapping("/task/v1")
public class TaskController {

	@Autowired
	private TskService service;

	@PostMapping("/registertask/{userid}")
	public ResponseEntity<TaskDTO> registerTask(@PathVariable(name = "userid") long userid,
			@RequestBody TaskDTO taskDTO) {
		TaskDTO dto = service.saveTask(userid, taskDTO);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@GetMapping("/gettasks/{userid}/{taskid}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable(name = "userid") long userid,
			@PathVariable(name = "taskid") long taskid) {

		return new ResponseEntity<>(service.getTask(userid, taskid), HttpStatus.OK);

	}
	@DeleteMapping("/deletetasks/{userid}/{taskid}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "userid") long userid,
			@PathVariable(name = "taskid") long taskid) {
		service.deleteTask(userid, taskid);
		return new ResponseEntity<>("Task delete Successfully", HttpStatus.OK);

	}
	
	@GetMapping("/getAllTasks")
	public ResponseEntity<List<Task>> getAllTask()
	{
		
		List<Task> task=service.getAllTasks();
		return new ResponseEntity<>(task,HttpStatus.OK);
	}

}
