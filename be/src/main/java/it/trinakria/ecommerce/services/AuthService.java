package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.dto.AccessModel;
import it.trinakria.ecommerce.model.dto.AuthResult;

public interface AuthService {
    AuthResult getToken(AccessModel user);
}
