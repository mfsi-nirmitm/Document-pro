package com.documentpro.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

//public class JwtFilter extends GenericFilterBean {
@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(jwtToken).getBody();
				username = claims.getSubject();
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} 
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			
			// if token is valid configure Spring  Security to manually set 
			// authentication
			if (username.equals(userDetails.getUsername())) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So is passes the 
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			} 
		}
		chain.doFilter(request, response);
	}

	
	
//	@Override
//	public void doFilter(ServletRequest req, 
//						 ServletResponse res, 
//						 FilterChain chain) throws IOException, ServletException {
//		
//		final HttpServletRequest request = (HttpServletRequest) req;
//		final HttpServletResponse response = (HttpServletResponse) res;
//		final String authHeader = request.getHeader("authorization");
//		
//		System.out.println(authHeader);
//		
//		if ( "OPTIONS".equals(request.getMethod()) ) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			
//			chain.doFilter(req, res);
//		} else {
//			
//			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//				throw new ServletException("Mission or invalid Authorization header");
//			}
//			
//			final String token = authHeader.substring(7);
//			
//			try {
//				final Claims claims = Jwts.parser().setSigningKey("secretKey")
//												   .parseClaimsJws(token)
//												   .getBody();
//				
//				request.setAttribute("claims", claims);
//			} catch ( final SignatureException e) {
//				throw new ServletException("Invalid token.");
//			}
//			
//			chain.doFilter(req, res);
//		}
//		
//	}

}
