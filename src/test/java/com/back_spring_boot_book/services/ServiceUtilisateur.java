package com.back_spring_boot_book.services;

import com.back_spring_boot_book.repository.RepositoryUtilisateur;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceUtilisateur {

    @Mock
    RepositoryUtilisateur repositoryUtilisateur;

    @InjectMocks
    ServiceUtilisateur serviceUtilisateur;
}
