package services.dto;

import entities.User;
import entities.enumerations.Role;

import java.util.List;

public class UsersResultDTO {
    List<User> results;
    List<Role> roles;

    public UsersResultDTO(List<User> results, List<Role> roles) {
        this.results = results;
        this.roles = roles;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
