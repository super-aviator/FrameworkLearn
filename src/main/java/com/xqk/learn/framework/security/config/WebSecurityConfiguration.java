package com.xqk.learn.framework.security.config;

import com.alibaba.fastjson.JSONObject;
import com.xqk.learn.framework.common.ResponseMessage;
import com.xqk.learn.framework.security.filter.JSONLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.io.PrintWriter;

/**
 * security的代码配置
 *
 * @author Aviator
 */
@Profile("security")
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    // private final UserDetailsService userDetailsService;
    // private final JdbcTokenRepositoryImpl jdbcTokenRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存的用于配置
        // auth.inMemoryAuthentication()
        //     // 5.x版本的SpringSecurity需要自定义密码编码器
        //     .passwordEncoder(new MyPasswordEncoder())
        //     .withUser("xqk")
        //     .password("123456")
        //     .roles("USER")
        //     .and()
        //     .withUser("yxm")
        //     .password("654321")
        //     .roles("ADMIN")
        //     .and()
        //     .withUser("cr")
        //     .password("123321")
        //     .roles("ADMIN", "USER");

        //自定义UserDetailService，用于从数据库获取用户信息
        // auth.jdbcAuthentication();
        // auth.userDetailsService(userDetailsService);
    }

    /**
     * 注意代码中配置的三条规则的顺序非常重要，和 Shiro 类似，Spring Security 在匹配的时候也是按照从上往下的顺序来匹配，一旦匹配到了就不继续匹配了，所以拦截规则的顺序不能写错
     *
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭认证
        http.csrf()
            .disable()
            .authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .logout()
            .permitAll();

        // http.authorizeRequests()
        /* *********************************权限相关**********************************/
        //路径为/user开头的url只能被ROLEUSER访问（ROLE）是Spring自动添加的
        // .antMatchers("/user/**")
        // .hasRole("USER")
        //路径为/time开头的url只能被ROLEADMIN访问（ROLE）是Spring自动添加的
        // .antMatchers("/admin/**")
        // .hasRole("ADMIN")
        // 路径为/path-variable开头的url只能被同时拥有ROLEADMIN和ROLEUSER角色的用户访问（ROLE）是Spring自动添加的,可以通过（and、or控制）
        // .antMatchers("/all/**")
        // .access("hasRole('ADMIN') or hasRole('USER')")
        // 其它任何没有进行匹配的URLs只需要用户认证过即可访问。
        // .anyRequest()
        // .authenticated()
        // .and()

        /* *********************************登录相关**********************************/
        //是否开启登录页面
        // .formLogin()
        // .and()
        //增加自动登录功能，并指定key，防止服务器重启之后cookies失效，并使用令牌持久化功能
        // .rememberMe()
        // .key("aviator")
        // .tokenRepository(jdbcTokenRepository)
        // .and()
        //指定登录接口页面地址
        // .formLogin().loginPage("/login")
        //指定登录接口地址,允许所有用户通过表单的方式登录（POST请求）
        // .loginProcessingUrl("/login")
        //指定登录用户名密码参数
        // .usernameParameter("username").passwordParameter("password")
        //defaultSuccessUrl 有一个重载的方法，我们先说一个参数的 defaultSuccessUrl 方法。如果我们在 defaultSuccessUrl 中指定登录成功的跳转页面为 /index，
        // 此时分两种情况，如果你是直接在浏览器中输入的登录地址，登录成功后，就直接跳转到 /index，
        // 如果你是在浏览器中输入了其他地址，例如 http://localhost:8080/hello，结果因为没有登录，又重定向到登录页面，此时登录成功后，就不会来到 /index ，而是来到 /hello 页面。
        // defaultSuccessUrl 还有一个重载的方法，第二个参数如果不设置默认为 false，也就是我们上面的的情况，如果手动设置第二个参数为 true，则 defaultSuccessUrl 的效果和 successForwardUrl 一致。
        // .defaultSuccessUrl("/index")
        //successForwardUrl 表示不管你是从哪里来的，登录后一律跳转到 successForwardUrl 指定的地址。例如 successForwardUrl 指定的地址为 /index ，你在浏览器地址栏输入 http://localhost:8080/hello，
        //结果因为没有登录，重定向到登录页面，当你登录成功之后，就会服务端跳转到 /index 页面；或者你直接就在浏览器输入了登录页面地址，登录成功后也是来到 /index。
        // .successForwardUrl("/index")
        //failureForwardUrl 是登录失败之后会发生服务端跳转
        // .failureForwardUrl("/failure")
        //failureUrl 则在登录失败之后，会发生重定向
        // .failureUrl("/failure")
        //开放上面指定的登录接口
        // .permitAll().and()

        /* *********************************注销相关**********************************/
        // .logout()
        //登出地址
        // .logoutUrl("/logout")
        //登录成功的url地址
        // .logoutSuccessUrl("/index")
        //清除cookie
        // .deleteCookies()
        //清楚认证信息（默认清理）
        // .clearAuthentication(true)
        //使HttpSession失效（默认失效）
        // .invalidateHttpSession(true)
        // .and()

        /* *********************************安全相关**********************************/
        // 关闭CSRF保护，否则POST请求必须带CSRF参数,不带的话请求会报403
        // .csrf()
        // .disable();
        //使用HttpBasic认证（Basic认证是一种较为简单的HTTP认证方式，采用明文传输，是不安全的）
        // .httpBasic();

        //添加自定义Filter
        // http.addFilterAt(jsonLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 配置静态资源忽略权限的路径
     *
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) {
        //配置忽略的文件URL地址路径
        web.ignoring()
           .antMatchers("/js/**", "/css/**", "/images/**");
    }

    /**
     * 为自定义的过滤器增加登录成功、失败拦截器以及登录url地址
     *
     * @return JSONLoginFilter
     * @throws Exception Exception
     */
    // @Bean
    public JSONLoginFilter jsonLoginFilter() throws Exception {
        JSONLoginFilter loginFilter = new JSONLoginFilter();
        //添加登录成功处理器
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication)->{
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("remoteAddress", details.getRemoteAddress());
            jsonObject.put("sessionId", details.getSessionId());
            jsonObject.put("message", "登录成功");

            out.write(JSONObject.toJSONString(ResponseMessage.ok(jsonObject)));
            out.flush();
            out.close();
        });
        //添加登录失败处理器
        loginFilter.setAuthenticationFailureHandler((request, response, exception)->{
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            ResponseMessage<String> message = null;
            if (exception instanceof LockedException) {
                message = ResponseMessage.generic(403, "账户被锁定，请联系管理员!");
            } else if (exception instanceof CredentialsExpiredException) {
                message = ResponseMessage.generic(403, "密码过期，请联系管理员!");
            } else if (exception instanceof AccountExpiredException) {
                message = ResponseMessage.generic(403, "账户过期，请联系管理员!");
            } else if (exception instanceof DisabledException) {
                message = ResponseMessage.generic(403, "账户被禁用，请联系管理员!");
            } else if (exception instanceof BadCredentialsException) {
                message = ResponseMessage.generic(403, "用户名或者密码输入错误，请重新输入!");
            }
            out.write(JSONObject.toJSONString(message));
            out.flush();
            out.close();
        });
        //指定AuthenticationManager
        loginFilter.setAuthenticationManager(authenticationManager());
        //指定登录url
        loginFilter.setFilterProcessesUrl("/login");
        return loginFilter;
    }
}
