package com.habeexdev.CountyRest.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habeexdev.CountyRest.Constant.AppConstants;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AppConstants appConstants;

    private static Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailOrphone) throws UsernameNotFoundException {

        logger.info("NOW STARTING AUTHENTICATION AT USER DETAILS SERVICE");


        logger.info("DONE WITH AUTHENTICATION AT USER DETAILS SERVICE " + emailOrphone);
        
        
        if(!emailOrphone.contains(appConstants.APP_DEFAULT_ADMIN_NAME)){
        	throw new UsernameNotFoundException("Email not found");
        }

        logger.info("NOW STARTING " + emailOrphone);
        return new org.springframework.security.core.userdetails.User(emailOrphone, passwordEncoder.encode(appConstants.APP_ADMIN_PASSWORD), getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges());
    }

    private List<String> getPrivileges() {
        List<String> privileges = new ArrayList<String>();
        privileges.add("admin");
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}