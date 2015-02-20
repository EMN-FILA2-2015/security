package demo.security;

import demo.domain.UserRole;

/**
 * Security : granted authority
 */
public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {

    /**
     * User role
     */
    private final UserRole role;

    /**
     * Constructor.
     * @param role User role
     */
    public GrantedAuthority(UserRole role) {
        this.role = role;
    }

    /**
     * Get role
     * @return role
     */
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Get authority
     * @return authority
     */
    @Override
    public String getAuthority() {
        return role.name();
    }

}
