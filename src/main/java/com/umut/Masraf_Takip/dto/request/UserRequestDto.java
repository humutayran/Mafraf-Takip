package com.umut.Masraf_Takip.dto.request;

import com.umut.Masraf_Takip.model.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserRequestDto {
    private String name;

    private String username;

    private String password;

    private String email;

    private Set<Role> roles = new HashSet<>();
}
