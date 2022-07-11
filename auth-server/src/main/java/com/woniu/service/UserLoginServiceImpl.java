package com.woniu.service;

import com.woniu.outlet.dao.mysql.po.UserLogin;
import com.woniu.outlet.dao.redis.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin users = userRepository.getUsers(username);

        // 获得用户所有权限
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (users != null) {
            if (users.getPermsList().size() > 0) {
                for (String perm : users.getPermsList()) {
                    if (perm != null && perm.length() > 0) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(perm);
                        grantedAuthorities.add(authority);
                    }
                }
            }
        } else
            throw new UsernameNotFoundException("用户名或密码错误");
        User user = new User(users.getAccount(), users.getPassword(),
                true, true, true,
                true, grantedAuthorities);
        return user;
    }
}
