package com.neonci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.neonci.dto.Todo;
import com.neonci.repository.TodoRepository;

@Controller
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@MutationMapping
	public Todo createTodo(@Argument String title) {
		Todo todo = new Todo();
		todo.setTitle(title);
		return todoRepository.save(todo);
	}

	@MutationMapping
	public Todo updateTodo(@Argument Integer id, @Argument String title, @Argument Boolean completed) {
		Todo todo = todoRepository.findById(Long.valueOf(id)).orElse(null);
		if (todo != null) {
			todo.setTitle(title);
			todo.setCompleted(completed);
			return todoRepository.save(todo);
		} else {
			return null; // Handle not found scenario
		}
	}

	@MutationMapping
	public Integer deleteTodo(@Argument Integer id) {
		todoRepository.deleteById(Long.valueOf(id));
		return id;
	}

}
