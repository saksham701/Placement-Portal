package com.iiita.placementportal.service;

import com.iiita.placementportal.dao.UserDao;
import com.iiita.placementportal.entity.JwtRequest;
import com.iiita.placementportal.entity.JwtResponse;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
         String userName = jwtRequest.getUserName();
         String password = jwtRequest.getPassword();
         authenticate(userName,password);

         final UserDetails userDetails = loadUserByUsername(userName);

         String newGeneratedToken = jwtUtil.generateToken(userDetails);
         User user =  userDao.findById(userDetails.getUsername()).get();

         return new JwtResponse(user,newGeneratedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     User user = userDao.findById(username).get();
     if(user!=null){
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),getAuthorities(user)
        );
     }
     else{
         throw new UsernameNotFoundException("User Email is not valid");
     }
    }

    private Set getAuthorities(User user){
        Set authorities = new HashSet<>();

        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String email,String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }
        catch (DisabledException e){
            throw new Exception("User is disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");
        }
    }
}
