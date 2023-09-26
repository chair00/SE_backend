package com.example.se.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//UserDetails는 구분해도 되고, 같은 클래스에서 관리해도 됨
public class Users extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    @Column
    private String userPw;

    @Column
    private String userName;

    @Column
    private String userMajor;

    @Column
    private String userState;

    private LocalDate userBirth;

    private String role; //여기서 role은 user밖에 없으므로 .. enum 안씀

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> this.getRole());
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userId;
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

    //getUsername() 함수가 원래 있어서 그런건지 모르겠는데, UserService.java에서 findUser할때 user.getUserName()이 없다고 떠서 새로 만들어줬음. 솔직히 이해안됨.
    public String getUserName() {
        return userName;
    }
}