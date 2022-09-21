// package com.xqk.learn.framework.security.config;
//
// import org.springframework.context.annotation.Profile;
// import org.springframework.security.authentication.AuthenticationServiceException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
//
// import javax.servlet.http.HttpServletRequest;
//
// /**
//  * 用于验证码的校验的Provider
//  *
//  * @author xiongqiankun
//  * @since 2021/9/28 16:47
//  */
// @Profile("security")
// @Component
// public class VerifyCodeProvider extends DaoAuthenticationProvider {
//     @Override
//     protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//         String code = request.getParameter("code");
//         String verifyCode = (String) request.getSession().getAttribute("verify_code");
//         if (code == null || !code.equals(verifyCode)) {
//             throw new AuthenticationServiceException("验证码错误");
//         }
//         super.additionalAuthenticationChecks(userDetails, authentication);
//     }
// }
