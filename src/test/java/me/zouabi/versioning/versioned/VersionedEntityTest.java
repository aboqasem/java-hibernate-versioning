package me.zouabi.versioning.versioned;

import lombok.extern.slf4j.Slf4j;
import me.zouabi.versioning.versioned.model.entity.VersionedDummyEntity;
import me.zouabi.versioning.versioned.model.repository.VersionedDummyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class VersionedEntityTest {

    @Autowired
    private VersionedDummyRepository repository;

    @Test
    void create() {
        final var entity = VersionedDummyEntity.builder()
                .name("Test Entity")
                .build();

        repository.save(entity);

        final var versionId = entity.getVersionId();
        Assertions.assertThat(versionId).isNotNull();
        Assertions.assertThat(versionId).isEqualTo(entity.getId());
        Assertions.assertThat(versionId.version()).isEqualTo(7);

        Assertions.assertThat(entity.getEntityId()).isNotNull();
    }

}
