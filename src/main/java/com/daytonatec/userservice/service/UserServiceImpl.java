package com.daytonatec.userservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

import com.daytonatec.userservice.config.UserContext;
import com.daytonatec.userservice.entity.User;
import com.daytonatec.userservice.repositoty.IUserREpository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	IUserREpository userREpository;

	@Override
	public List<User> getAllUser() {
		return userREpository.findAll();
	}

	@Override
	public User update(User u) {
		Optional<User> obj = userREpository.findById(u.getId());
		if (!obj.isEmpty()) {
			User oldData = obj.get();
			oldData.setModifiedBy(UserContext.getCurrentUser().toString());
			oldData.setModifiedOn(new Date());
			oldData.setDob(u.getDob());
			//oldData.setEmail(u.getEmail());
			oldData.setFirstName(u.getFirstName());
			oldData.setLastName(u.getLastName());
			oldData.setStatus("updated");
			u = userREpository.save(oldData);
		} else {
			u.setStatus("invalid user-id");
		}
		return u;
	}

	@Override
	public User save(User u) {
		u.setPassword(this.generatePassword());
		u.setCreatedOn(new Date());
		u.setModifiedOn(new Date());
		u.setStatus("created");
		return userREpository.save(u);
	}

	@Override
	public User getUserByEmail(String email) {
		return userREpository.findByEmail(email);
	}

	@Override
	public String updatePassword(String email, String oldPassword, String newPassword) {
		User user = this.getUserByEmail(email);
		return (user != null
				? (user.getPassword().equals(oldPassword) ? this.validateAndUpdatePassword(newPassword, user)
						: "invalid old password")
				: "invalid email");
	}

	private String validateAndUpdatePassword(String newPassword, User user) {
		StringBuffer sb = new StringBuffer();
		if (this.isValidPassword(newPassword)) {
			user.setPassword(newPassword);
			this.update(user);
			sb.append("password updated");
		} else {
			sb.append("invalid password format");
		}
		return sb.toString();
	}

	public boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);
		if (password == null) {
			return false;
		}
		Matcher m = p.matcher(password);
		return m.matches();
	}

	private String generatePassword() {
		PasswordGenerator gen = new PasswordGenerator();

		CharacterData alphabeticalCaseChars = EnglishCharacterData.Alphabetical;
		CharacterRule alphabeticalCaseRule = new CharacterRule(alphabeticalCaseChars);
		alphabeticalCaseRule.setNumberOfCharacters(1);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(1);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return "ERROR_CODE";
			}

			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(1);

		String password = gen.generatePassword(8, splCharRule, alphabeticalCaseRule, digitRule);
		return password;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = this.getUserByEmail(email);
		return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), new ArrayList<>());
	}

}
