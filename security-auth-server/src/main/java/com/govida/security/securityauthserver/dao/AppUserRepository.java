package com.govida.security.securityauthserver.dao;

import com.govida.security.securityauthserver.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser, String> {

}