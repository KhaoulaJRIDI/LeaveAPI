package com.example.leaveapi.service;

import com.example.leaveapi.dto.UserDTO;
import com.example.leaveapi.entity.User;
import com.example.leaveapi.exceptions.NotFoundException;
import com.example.leaveapi.repository.UserRepository;
import com.example.leaveapi.enums.Role;
import com.example.leaveapi.exceptions.DuplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new NotFoundException("User not found: " + id));
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("User not found: " + email));
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new NotFoundException("User not found: " + username));
	}
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User create(User User) {
		User.setId(null);
		User.addRole(Role.USER);
		checkEmailDuplication(User);
		return userRepository.save(User);
	}

	public UserDTO create(UserDTO dto) {
		return new UserDTO(create(new User(dto)));
	}

	public User update(User User) {
		checkEmailDuplication(User);
		User p = findById(User.getId());
		p.setUsername(User.getUsername());
		p.setEmail(User.getEmail());
		p.setRoles(User.getRoles());
		return userRepository.save(p);
	}

	public void delete(Long id) {
		final User p = findById(id);
		userRepository.delete(p);
	}

	private void checkEmailDuplication(User User) {
		final String email = User.getEmail();
		if (email != null && email.length() > 0) {
			final Long id = User.getId();
			final User p = userRepository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationException("Email duplication: " + email);
			}
		}
	}

}
