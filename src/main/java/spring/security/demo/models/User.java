package spring.security.demo.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Integer userId;

    @Column(unique=true)
    private String username;
    
    private String password; 

    @Version
    private Integer version;

    // Many users can have many roles and many roles can have many users. We want to fetch data for the authorities as soon as we fetch the user information (EAGER)
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

    // No arg constructor
    public User(){
        super();
        this.authorities = new HashSet<Role>();
    }

    // All arg Constructor
    public User(Integer userId, String username, String password, Set<Role> authorities){
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // Override implemented methods 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
    
}
