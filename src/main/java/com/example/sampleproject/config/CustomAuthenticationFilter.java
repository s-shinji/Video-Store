// package com.example.sampleproject.config;

// import javax.servlet.FilterChain;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.example.sampleproject.entity.Account;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

// 	@Override
// 	public Authentication attemptAuthentication(HttpServletRequest request,
// 		HttpServletResponse response) {
// //         String jsonString = "{\"userName\":\"bell\",\"password\":\"belldesu\"}";
// 	  try {
// 		Account account = new ObjectMapper().readValue(request.getInputStream(),
// 			Account.class);
  
// 		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
// 			account, null);
  
// 		setDetails(request, authRequest);
  
// 		return this.getAuthenticationManager().authenticate(authRequest);
  
// 	  } catch (Exception e) {
// 		throw new RuntimeException(e);
// 	  }
// 	}

// 	@Override
// 	protected void successfulAuthentication(HttpServletRequest req,
// 		HttpServletResponse res,
// 		FilterChain chain,
// 		Authentication auth) {
  
// 	  SecurityContextHolder.getContext().setAuthentication(auth);
  
// 	}
//   }
  
  