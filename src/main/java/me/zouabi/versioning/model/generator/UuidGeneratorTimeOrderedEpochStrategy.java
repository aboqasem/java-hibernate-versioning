package me.zouabi.versioning.model.generator;

import com.github.f4b6a3.uuid.UuidCreator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.util.UUID;

public class UuidGeneratorTimeOrderedEpochStrategy implements UuidGenerator.Strategy {
    @Override
    public int getUuidVersion() {
        return 7;
    }

    @Override
    public UUID generateUuid(SharedSessionContractImplementor session) {
        return UuidCreator.getTimeOrderedEpoch();
    }
}
