package me.zouabi.versioning.model.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.util.UUID;

public class UuidGeneratorRandomStrategy implements UuidGenerator.Strategy {
    @Override
    public int getUuidVersion() {
        return 4;
    }

    @Override
    public UUID generateUuid(SharedSessionContractImplementor session) {
        return UUID.randomUUID();
    }
}
