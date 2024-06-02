package com.usermanagement.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.usermanagement.models.UserInfo;
import com.usermanagement.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
		if (userInfo.isPresent()) {
			return new UserInfoUserDetails(userInfo.get());
		} else {
			throw new UsernameNotFoundException("User not found " + username);
		}
	}

	private String[] getUserRoles(UserInfo userInfo) {
		if (userInfo.getRoles() == null) {
			return new String[] { "ROLE_USER" };
		}
		return userInfo.getRoles().split(",");
	}

}
