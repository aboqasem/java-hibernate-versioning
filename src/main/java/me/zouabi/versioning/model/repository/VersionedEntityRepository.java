package me.zouabi.versioning.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface VersionedEntityRepository<T> extends JpaRepository<T, UUID> {
}
