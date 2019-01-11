package com.itacademy.jd2.mm.auction.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.service.utils.PasswordUtils;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

		IUserAccount account = userAccountService.getUserByLogin(username);
		if (account == null) {
			throw new BadCredentialsException("1000");
		}
		if (!PasswordUtils.verifyUserPassword(password, account.getPassword())) {
			throw new BadCredentialsException("1000");
		}

		final int userId = account.getId();

		List<String> userRoles = new ArrayList<>();
		userRoles.add("ROLE_" + account.getRole());

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
		return new ExtendedUsernamePasswordAuthenticationToken(userId, username, password, authorities);
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
