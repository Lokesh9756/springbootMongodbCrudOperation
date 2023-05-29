package javaproject.springbootmongocrudoperation.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javaproject.springbootmongocrudoperation.model.User;
import javaproject.springbootmongocrudoperation.repository.UserRepository;

@RestController
public class userController {
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/users")
	public ResponseEntity<?> getAllusers() {
		List<User> users = userRepo.findAll();

		if (users.size() > 0) {
			Collections.sort(users, (user1, user2) -> {
				return user1.getDateOfBirth().compareTo(user2.getDateOfBirth());
			});
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No user available", HttpStatus.NOT_FOUND);

		}
	}

	@PostMapping("/users")
	public ResponseEntity<?> createuser(@RequestBody User user) {
		try {
			user.setCreatedAt(new Date(System.currentTimeMillis()));
			userRepo.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/users/{username}")
	public ResponseEntity<?> updateById(@PathVariable("username") String username, @RequestBody User user) {
		Optional<User> userOptional = userRepo.findById(username);
		if (userOptional.isPresent()) {
			User userToSave = userOptional.get();
			userToSave.setUserName(user.getUserName() != null ? user.getUserName() : userToSave.getUserName());
			userToSave.setName(user.getName() != null ? user.getName() : userToSave.getName());
			userToSave.setDateOfBirth(
					user.getDateOfBirth() != null ? user.getDateOfBirth() : userToSave.getDateOfBirth());
			userToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
			userRepo.save(userToSave);
			return new ResponseEntity<>(userToSave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("user is not available by username", HttpStatus.NOT_FOUND);
		}
	}
}
