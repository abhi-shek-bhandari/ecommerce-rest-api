package com.masai.demo.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotNull(message = "First Name cannot be null.")
    @NotBlank(message = "First Name cannot be blank.")
    @NotEmpty(message = "First Name cannot be empty.")
    private String userFirstName;

    @NotNull(message = "Last Name cannot be null.")
    @NotBlank(message = "Last Name cannot be blank.")
    @NotEmpty(message = "Last Name cannot be empty.")
    private String userLastName;

    @Email
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @NotEmpty(message = "Email cannot be empty")
    @Column(unique = true)
//    @UniqueElements
//    @UniqueElements(message = "Email Already Registered")

    private String email;

    @NotNull
//    @Pattern(regexp="^[A-Z][a-z]*",message = "Password must contain min 6 characters, 1 Upper Case and 1 Lower Case and 1 Digit")
    private String password;

    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be blank")
    @NotEmpty(message = "Phone cannot be empty")
    @Size(min=10,max=10,message="Mobile should be 10 characters.")
    @Pattern(regexp = "^[6-9][0-9]{9}",message="Mobile should contains only numbers")
    private String phone;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Orders> orders = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authories = this.roles.stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return authories;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
