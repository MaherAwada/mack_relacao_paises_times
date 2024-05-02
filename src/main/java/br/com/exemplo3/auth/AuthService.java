package br.com.exemplo3.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.exemplo3.config.JwtService;
import br.com.exemplo3.user.User;
import br.com.exemplo3.user.UserRepository;

@Service
public class AuthService {
	
	@Autowired UserRepository repository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired JwtService jwtService;
	@Autowired AuthenticationManager authenticationManager;
	
	public AuthResponse register(RegisterRequest request) {
		var user = repository.save(new User()
				.setId(UUID.randomUUID())
				.setEmail(request.getEmail())
				.setPassword(passwordEncoder.encode(request.getPassword())));
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		return new AuthResponse().setAccessToken(jwtToken).setRefreshToken(refreshToken);
	}

	public AuthResponse authenticate(AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		return new AuthResponse().setAccessToken(jwtToken).setRefreshToken(refreshToken);
	}
	
}
