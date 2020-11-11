package edu.example.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SecurityUserDetailsManager implements UserDetailsManager {
    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // тут должна быть логика
        ArrayList<String> userNames = new ArrayList<>();
        userNames.add("user");
        userNames.add("userbook");
        userNames.add("userauthor");
        if ( !userNames.contains(userName))
            return null;

        List<SecurityPermission> permissionsAuthor = new ArrayList<>();
        permissionsAuthor.add(new SecurityPermission("author.read"));
        permissionsAuthor.add(new SecurityPermission("author.edit"));
        permissionsAuthor.add(new SecurityPermission("author.delete"));

        List<SecurityPermission> permissionsBook = new ArrayList<>();
        permissionsBook.add(new SecurityPermission("book.read"));
        permissionsBook.add(new SecurityPermission("book.edit"));
        permissionsBook.add(new SecurityPermission("book.delete"));

        List<SecurityPermission> permissionsAll = new ArrayList<>();
        permissionsAll.addAll(permissionsAuthor);
        permissionsAll.addAll(permissionsBook);

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        switch (userName) {
            case ("user"):
                return new SecurityUser(userName, encoder.encode("123"), permissionsAll);
            case ("userbook"):
                return new SecurityUser(userName, encoder.encode("1234"), permissionsBook);
            case ("userauthor"):
                return new SecurityUser(userName, encoder.encode("12345"), permissionsAuthor);
            default:
                return null;
        }


    }
}
