package entities;

import entities.enumerations.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String profilePictureUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="student", fetch = FetchType.EAGER)
    private Set<ExamResult> examResults;

    public User(String firstName, String lastName, String email, String profilePictureUrl, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Set<ExamResult> getExamResults() {
        return examResults;
    }

    public void setExamResults(Set<ExamResult> examResults) {
        this.examResults = examResults;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
