package model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.UUID;

public class CustomUserDetails extends User {

    private final UUID id;

    public CustomUserDetails(UUID id, String name, String password, Collection<? extends GrantedAuthority> authorities){
        super(name,password,authorities);
        this.id=id;
    }

    public UUID getId() {
        return id;
    }
}
