package info.desabre.application.security;

/**
 * Created by MikaSez on 31/05/2015.
 */
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public boolean isRole(String roleName) {
        return (this.roleName.equals(roleName));
    }

    public static Role fromName(String name) {
        for (Role r : Role.values()) {
            if (r.isRole(name)) {
                return r;
            }
        }
        throw new IllegalArgumentException("There are no Role for name : " + name);
    }

    public String getRoleName() {
        return roleName.substring(5);
    }

    public String toString() {
        return roleName;
    }


}
