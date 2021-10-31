package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAuth {

  private UUID id;
  private String email;
  private String password;
  private List<Role> roles = new ArrayList<>();
  private boolean active;
  private UserProfile userProfile;


  public UserAuth(String email, String password, UserProfile userProfile) {
    this.roles.add(new Role("ROLE_USER"));
    setUserProfile(userProfile);
    setEmail(email);
    setPassword(password);
  }

  public UserAuth(String email, String password, List<Role> roles, UserProfile userProfile) {
    setEmail(email);
    setPassword(password);
    setUserProfile(userProfile);
    setRoles(roles);
  }

  public UserAuth(UUID id, String email, String password, List<Role> roles, boolean active, UserProfile userProfile) {
    this.id = id;
    this.active = active;
    setRoles(roles);
    setUserProfile(userProfile);
    setEmail(email);
    setPassword(password);
  }

  public void confirmEmail() {
    this.active = true;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  // TODO: implement RFC822: http://www.ex-parrot.com/pdw/Mail-RFC822-Address.html
  private boolean isValidEmail(String email) {
    String regex = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    return pattern.matcher(email).matches();
  }

  public void setEmail(String email) {

    if (email == null || email.isBlank())
      throw new InvalidAttributeException("Email cannot be blank");

    if (!isValidEmail(email)) {
      throw new InvalidAttributeException("The email address entered is not valid");
    }

    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {

    if (password == null || password.isBlank())
      throw new InvalidAttributeException("Password cannot be blank");

    if (password.length() < 4)
      throw new InvalidAttributeException("Password must contain more than 4 characters");

    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> role) {
    if (role == null || role.isEmpty()) throw new InvalidAttributeException("Role cannot be empty");
    this.roles = role;
  }

  public boolean isActive() {
    return active;
  }

  public UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(UserProfile userProfile) {
    if (userProfile == null) throw new InvalidAttributeException("UserProfile cannot be empty");
    this.userProfile = userProfile;
  }
}
