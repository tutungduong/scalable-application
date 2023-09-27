//package com.javaguides.scalable.security;
//
//import com.javaguides.scalable.entity.User;
//import com.javaguides.scalable.repository.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.stream.Collectors;
//
//@Service
//@Transactional (readOnly = true)
//public class CustomUserDetailsService implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(usernameOrEmail);
//        if(user != null){
//            return new org.springframework.security.core.userdetails.User(user.getEmail()
//                    ,user.getPassword(),
//                    user.getRoles().stream()
//                            .map((role) -> new SimpleGrantedAuthority("ROLE_"+ role.getRoleName().toString()))
//                            .collect(Collectors.toList()));
//        }
//        else{
//            throw new UsernameNotFoundException("Invalid email or password");
//        }
//    }
//}
