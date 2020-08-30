package com.example.sampleproject.config;

import java.util.Arrays;


// import com.example.sampleproject.entity.Image;
// import com.example.sampleproject.entity.MemberRegistrationEntity;
// import com.example.sampleproject.entity.Movie;
// import com.example.sampleproject.entity.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Autowired
    // CustomAuthenticationProvider authenticationProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //ログインページを指定。
        //ログインページへのアクセスは全員許可する。
        http            .cors()        .configurationSource(this.corsConfigurationSource())
        .and()
        .formLogin() //ログイン設定
        
            //springとreactでurlがかぶらないようにするため追加
            .loginPage("/KdiJ362/login")
            .loginProcessingUrl("/KdiJ362/authenticate")
            .usernameParameter("userName")
            .passwordParameter("password")
			// .defaultSuccessUrl("http://localhost:3000/index")
			.defaultSuccessUrl("/KdiJ362/auth")
			.failureUrl("/KdiJ362/login-error")
            .permitAll();
        
		
		http.logout()
			.logoutSuccessUrl("/KdiJ362/logouted")
			.permitAll();
        // CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        // filter.setRequiresAuthenticationRequestMatcher(
        //     new AntPathRequestMatcher("/authenticate", "POST"));
        // filter.setAuthenticationManager(authenticationManagerBean());

		http.csrf().disable().authorizeRequests()//.csrf().disable()でCSRFを無効にし、.authorizeRequests()で以下に記述するパスは認証不要でアクセスできるようにする
			//antMatchers().permitAll()で記載したURLへはログインなしで入れる
            .antMatchers("/css/**", "/images/**", "/js/**").permitAll()

            //springとreactでurlがかぶらないようにするため追加
            .antMatchers("/KdiJ362/RegistrationForm").permitAll()
            .antMatchers("/KdiJ362/Register").permitAll()
            .antMatchers("/KdiJ362/Result").permitAll()
            .antMatchers("/KdiJ362/index").permitAll()
            .antMatchers("/KdiJ362/video/{id}").permitAll()
            .antMatchers("/KdiJ362/").permitAll()
            .antMatchers("/KdiJ362/top").permitAll()
            .antMatchers("/KdiJ362/search").permitAll()
            //Reactから遷移できるように追加
            .antMatchers("/KdiJ362/user/{id}").permitAll()
            .antMatchers("/KdiJ362/upload").permitAll()
            .antMatchers("/KdiJ362/delete").permitAll()
            .antMatchers("/KdiJ362/authenticate").permitAll();
            // .antMatchers("/getBirthStoneList").permitAll()
			//anyRequest().authenticated()でその他の全てのページへはログインが必要にする
            // .anyRequest().authenticated()
            // .and()
            // .logout()
            // .logoutRequestMatcher(
            //     new AntPathRequestMatcher("logout", "POST"))
            // .and()
            // .addFilter(filter);

        //試験的導入
        // ログインパラメーターの設定
        // JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter =
        //     new JsonUsernamePasswordAuthenticationFilter(authenticationManager());
        // jsonUsernamePasswordAuthenticationFilter.setUsernameParameter("userName");
        // jsonUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
        // // ログイン後にリダイレクトのリダイレクトを抑制
        // jsonUsernamePasswordAuthenticationFilter
        //     .setAuthenticationSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK));
        //     // .setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/index"));
        // // ログイン失敗時のリダイレクト抑制
        // jsonUsernamePasswordAuthenticationFilter
        //     // .setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
        //     .setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/top"));
        // // FormログインのFilterを置き換える
        // http.addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // // Spring Securityデフォルトでは、アクセス権限（ROLE）設定したページに未認証状態でアクセスすると403を返すので、
        // // 401を返すように変更
        // // http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        // // 今回は、403エラー時にHTTP Bodyを返さないように設定
        // // http.exceptionHandling().accessDeniedHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN));

        // // ログアウト
        // http
        //     .logout()
        //     .logoutUrl("/logout")
        //     // ログアウト時のリダイレクト抑制
        //     .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
        //     .invalidateHttpSession(true);

    }
    
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        // configuration.setAllowedHeaders(Arrays.asList(  // CORSリクエストで受信を許可するヘッダー情報(以下は例です)
		// 		"Access-Control-Allow-Headers",
		// 		"Access-Control-Allow-Origin",
		// 		"Access-Control-Request-Method",
		// 		"Access-Control-Request-Headers",
		// 		"Cache-Control",
		// 		"Content-Type",
		// 		"Accept-Language"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailsService)//.userDetailsServiceはAuthenticationManagerBuilderが持つメソッドで引数のuserDetailsServiceは@Autowiredで挿入したもの？
                                                //    userDetailsServiceをオーバーライドしているためオーバーライドされた内容が追加されている
            .passwordEncoder(passwordEncoder());//.passwordEncoderはAuthenticationManagerBuilderが持つメソッドで引数のpasswordEncoder()は自分で@Beanで定義したもの
    }

    // @Bean
    // Movie movie () {
    //     return new Movie();
    // }

    // @Bean
    // Image image () {
    //     return new Image();
    // }

    // @Bean
    // MemberRegistrationEntity entity () {
    //     return new MemberRegistrationEntity();
    // }

    // @Bean
    // Review review () {
    //     return new Review();
    // }
}