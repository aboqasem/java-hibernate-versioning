package me.zouabi.versioning.model.entity.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.zouabi.versioning.model.generator.GeneratedUuid;
import me.zouabi.versioning.model.generator.UuidGeneratorTimeOrderedEpochStrategy;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class VersionedEntity implements Identifiable<UUID>, Serializable {

    @Id
    @GeneratedUuid(strategy = UuidGeneratorTimeOrderedEpochStrategy.class)
    private UUID versionId;

    @GeneratedUuid
    private UUID entityId;

    @Transient
    @Override
    public final UUID getId() {
        return getVersionId();
    }

    @Override
    public final void setId(UUID uuid) {
        setVersionId(uuid);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // Hibernate.getClass() is used to compare classes
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        final var thisId = this.getId();
        if (thisId == null) {
            return false;
        }

        final var thatId = ((VersionedEntity) obj).getId();

        return thisId.equals(thatId);
    }

    @Override
    public int hashCode() {
        final var thisId = this.getId();

        // prevent transient entities from having same hashcode
        return thisId == null ? super.hashCode() : thisId.hashCode();
    }
}
