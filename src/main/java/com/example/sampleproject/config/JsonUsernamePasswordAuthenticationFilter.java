// package com.example.sampleproject.config;

// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Optional;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.example.sampleproject.entity.DbUserDetails;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//     ObjectMapper objectMapper = new ObjectMapper();

//     String usernameParameter = "username";
//     String passwordParameter = "password";

//     public JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
//         super(new AntPathRequestMatcher("/authenticate", "POST"));
//         this.setAuthenticationManager(authenticationManager);
//     }

//     public String getUsernameParameter() {
//         return usernameParameter;
//     }

//     public void setUsernameParameter(String usernameParameter) {
//         this.usernameParameter = usernameParameter;
//     }

//     public String getPasswordParameter() {
//         return passwordParameter;
//     }

//     public void setPasswordParameter(String passwordParameter) {
//         this.passwordParameter = passwordParameter;
//     }

//     @SuppressWarnings("unchecked")
// 	@Override
// 	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//         Map<String, Object> requestObject;
//         // DbUserDetails dbUserDetails;
//         // String jsonString = "{\"userName\":\"bell\",\"password\":\"belldesu\"}";
//         try {
//             requestObject = objectMapper.readValue(request.getInputStream(), Map.class);
//         } catch (IOException e) {
//             requestObject = new HashMap<>();
//         }
//         // try {
//         //     dbUserDetails = objectMapper.readValue(request.getInputStream(), DbUserDetails.class);
//         // } catch (IOException e) {
//         //     dbUserDetails = null;
//         // }

//         String username =
//                 Optional
//                         .ofNullable(requestObject.get(usernameParameter))
//                         .map(Object::toString)
//                         .map(String::trim)
//                         .orElse("");
//         String password =
//                 Optional
//                         .ofNullable(requestObject.get(passwordParameter))
//                         .map(Object::toString)
//                         .orElse("");
//         // String username =
//         //         Optional
//         //                 .ofNullable(dbUserDetails.getUsername())
//         //                 .map(Object::toString)
//         //                 .map(String::trim)
//         //                 .orElse("");
//         // String password =
//         //         Optional
//         //                 .ofNullable(dbUserDetails.getPassword())
//         //                 .map(Object::toString)
//         //                 .orElse("");

//         UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//                 username, password);

//         authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        
//         return this.getAuthenticationManager().authenticate(authRequest);
//     }
// }