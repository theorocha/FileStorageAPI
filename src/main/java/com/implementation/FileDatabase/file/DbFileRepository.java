package com.implementation.FileDatabase.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DbFileRepository extends JpaRepository<DbFile, UUID> {
}
