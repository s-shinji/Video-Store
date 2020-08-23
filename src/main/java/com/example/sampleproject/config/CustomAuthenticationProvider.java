// package com.example.sampleproject.config;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;

// import com.example.sampleproject.entity.Account;
// import com.example.sampleproject.entity.DbUserDetails;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @Configuration
// public class CustomAuthenticationProvider implements AuthenticationProvider {

//   // ユーザ情報リポジトリ
//   @Autowired
//   UserDetailsService userDetailsService;

//   // テナント情報リポジトリ
// //   @Autowired
// //   TenantRepository tenantRepository;

//   @Override
//   public Authentication authenticate(Authentication authentication) throws AuthenticationException {

//     // CustomAuthenticationFilterクラスから渡されたものを取得
//     // Account account = (Account) authentication.getAccount();
//     Account account = (Account) authentication.getPrincipal();

//     if (account == null) {
//       throw new BadCredentialsException("認証情報がありません");
// 	}
	
// 	 // テナントコードの有効チェック
// 	//  Tenant tenant = tenantRepository.findByTenantCode(principal.getTenantCode());
// 	//  if (tenant == null || tenant.getStatus() == 0) {
// 	//    throw new BadCredentialsException("テナントが無効です");
// 	//  }
 
//  // メールアドレスとテナントコードでユーザの有効チェック
//  UserDetails user = userDetailsService.loadUserByUsername(account.getName());
 
// 	 // ユーザー情報を取得できなかった場合
// 	 if (user == null) {
// 	   throw new BadCredentialsException("ユーザが存在しません");
// 	 }
 
// 	 if (!new BCryptPasswordEncoder().matches(account.getPassword(), user.getPassword())) {
// 	   throw new BadCredentialsException("パスワードが間違っています");
// 	 }
// 	 List<GrantedAuthority> authorityList = new ArrayList<>();
// 	//  private Collection<GrantedAuthority> getAuthorities(Account account) {
// 	// 	//認可が通った時にこのユーザに与える権限の範囲を設定する。
// 	// 	return AuthorityUtils.createAuthorityList("ROLE_USER");
// 	// }

// 	 // 権限付与処理                                   //ここがおかしいかも
// 	  return new UsernamePasswordAuthenticationToken(account.getName(),account.getPassword(), authorityList);
// 	//  loginUser.setUserId(user.getId());
// 	//  loginUser.setUserName(user.getUserName());
 
// 	//  return new UsernamePasswordAuthenticationToken(loginUser, principal.getPassword(),
// 	// 	 authorityList);
//    }
 
//    @Override
//    public boolean supports(Class<?> authentication) {
// 	 return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
 
//  }