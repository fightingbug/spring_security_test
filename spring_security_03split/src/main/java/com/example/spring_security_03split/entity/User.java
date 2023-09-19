package com.example.spring_security_03split.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable, UserDetails {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Boolean enabled;

    /**
     * 
     */
    private Boolean accountnonexpired;

    /**
     * 
     */
    private Boolean accountnonlocked;

    /**
     * 
     */
    private Boolean creddentialsnonoexpired;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



    private List<Role> roles = new ArrayList<>();





    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getAccountnonexpired() == null ? other.getAccountnonexpired() == null : this.getAccountnonexpired().equals(other.getAccountnonexpired()))
            && (this.getAccountnonlocked() == null ? other.getAccountnonlocked() == null : this.getAccountnonlocked().equals(other.getAccountnonlocked()))
            && (this.getCreddentialsnonoexpired() == null ? other.getCreddentialsnonoexpired() == null : this.getCreddentialsnonoexpired().equals(other.getCreddentialsnonoexpired()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getAccountnonexpired() == null) ? 0 : getAccountnonexpired().hashCode());
        result = prime * result + ((getAccountnonlocked() == null) ? 0 : getAccountnonlocked().hashCode());
        result = prime * result + ((getCreddentialsnonoexpired() == null) ? 0 : getCreddentialsnonoexpired().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", accountnonexpired=" + accountnonexpired +
                ", accountnonlocked=" + accountnonlocked +
                ", creddentialsnonoexpired=" + creddentialsnonoexpired +
                ", roles=" + roles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(role.getName());
            authorities.add(simpleGrantedAuthority);

        });


        return null;
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
        return this.accountnonexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountnonlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled ;
    }
}