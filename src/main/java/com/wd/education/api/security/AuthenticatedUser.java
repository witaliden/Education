package com.wd.education.api.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Holds the info for a authenticated user (Principal).
 */
public class AuthenticatedUser implements UserDetails {

  @Serial
  private static final long serialVersionUID = -1831799558180677515L;

  private final String username;
  private final String token;
  private final Long userId;
  private final List<String> permissions;

  private final Collection<? extends GrantedAuthority> authorities;

  public AuthenticatedUser(
      final String username,
      final String token,
      final Long userId,
      final List<String> permissions, Collection<? extends GrantedAuthority> authorities
  ) {
    this.username = username;
    this.token = token;
    this.userId = userId;
    this.permissions = permissions;
    this.authorities = authorities;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }

  public String getToken() {
    return token;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  public Long getUserId() {
    return userId;
  }

  public List<String> getPermissions() {
    return permissions;
  }
}
