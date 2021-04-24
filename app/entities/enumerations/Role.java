package entities.enumerations;

public enum Role {
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    public final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
