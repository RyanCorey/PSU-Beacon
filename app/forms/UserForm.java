package forms;

import entities.enumerations.Role;

public class UserForm {
    private String firstName;

    private String lastName;

    private String email;

    private String profilePictureUrl;

    private Role role;

    public UserForm() {
    }

    public UserForm(String firstName, String lastName, String email, String profilePictureUrl, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
