/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package me.zouabi.versioning.model.generator;

import jakarta.persistence.PersistenceException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;
import org.hibernate.type.descriptor.java.UUIDJavaType;
import org.hibernate.type.descriptor.java.UUIDJavaType.ValueTransformer;

import java.lang.reflect.Member;
import java.util.EnumSet;
import java.util.UUID;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;
import static org.hibernate.internal.util.ReflectHelper.getPropertyType;

public class UuidGenerator implements BeforeExecutionGenerator {
    public interface Strategy {
        int getUuidVersion();

        UUID generateUuid(SharedSessionContractImplementor session);
    }

    @Getter(AccessLevel.PROTECTED)
    private final Strategy strategy;

    @Getter(AccessLevel.PROTECTED)
    private final ValueTransformer transformer;

    private UuidGenerator(GeneratedUuid config, Member idMember) {
        this.strategy = getStrategy(config);
        this.transformer = getTransformer(idMember);
    }

    @SuppressWarnings("unused") // Used by Hibernate
    public UuidGenerator(GeneratedUuid config, Member idMember, CustomIdGeneratorCreationContext ctx) {
        this(config, idMember);
    }

    @SuppressWarnings("unused") // Used by Hibernate
    public UuidGenerator(GeneratedUuid config, Member member, GeneratorCreationContext ctx) {
        this(config, member);
    }

    @SneakyThrows
    protected Strategy getStrategy(GeneratedUuid config) {
        return config.strategy().getDeclaredConstructor().newInstance();
    }

    protected ValueTransformer getTransformer(Member idMember) {
        final Class<?> propertyType = getPropertyType(idMember);
        if (UUID.class.isAssignableFrom(propertyType)) {
            return UUIDJavaType.PassThroughTransformer.INSTANCE;
        } else if (String.class.isAssignableFrom(propertyType)) {
            return UUIDJavaType.ToStringTransformer.INSTANCE;
        } else if (byte[].class.isAssignableFrom(propertyType)) {
            return UUIDJavaType.ToBytesTransformer.INSTANCE;
        } else {
            throw new PersistenceException("Unanticipated return type [" + propertyType.getName() + "] for UUID conversion");
        }
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        if (currentValue != null && this.transformer.parse(currentValue).version() == this.strategy.getUuidVersion()) {
            return currentValue;
        }

        return transformer.transform(strategy.generateUuid(session));
    }
}
