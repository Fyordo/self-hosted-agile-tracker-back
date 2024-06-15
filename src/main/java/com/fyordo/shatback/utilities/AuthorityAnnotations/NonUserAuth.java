package com.fyordo.shatback.utilities.AuthorityAnnotations;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasAnyAuthority(T(com.fyordo.shatback.utilities.Role).ADMIN, T(com.fyordo.shatback.utilities.Role).DIRECTOR)")
public @interface NonUserAuth {
}
