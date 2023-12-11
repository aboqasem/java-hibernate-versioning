package me.zouabi.versioning.model.generator;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(UuidGenerator.class)
@ValueGenerationType(generatedBy = UuidGenerator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface GeneratedUuid {

    Class<? extends UuidGenerator.Strategy> strategy() default UuidGeneratorRandomStrategy.class;

}
