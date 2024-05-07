package com.pb.pblog.config.auth;

import com.pb.pblog.dto.UserDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {


    private UserDTO userDTO;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth=new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(userDTO.getRole()));
        return auth;
    }

    //비밀번호
    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    //아이디
    @Override
    public String getUsername() {
        return userDTO.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    //닉네임 설정
    public String getNickname(){
        return userDTO.getNickname();
    }

    public void setNickname(String nickname){
        userDTO.setNickname(nickname);
    }
}
