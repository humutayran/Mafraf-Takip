package com.umut.Masraf_Takip.dto.request;

import com.umut.Masraf_Takip.model.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserRequestDto {

    @NotEmpty(message = "Isim boş bırakılamaz")
    private String name;

    @NotEmpty(message = "Kullanici adi boş bırakılamaz")
    private String username;

    @NotEmpty(message = "Sifre boş bırakılamaz")
    private String password;

    @NotEmpty(message = "Email boş bırakılamaz")
    private String email;

    @NotNull(message = "Rol boş bırakılamaz")
    private Set<Role> roles = new HashSet<>();
}
