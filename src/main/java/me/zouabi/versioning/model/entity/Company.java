package me.zouabi.versioning.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.zouabi.versioning.model.entity.base.VersionedEntity;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Company extends VersionedEntity {

    private String name;

}
