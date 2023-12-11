package me.zouabi.versioning;

import lombok.extern.slf4j.Slf4j;
import me.zouabi.versioning.model.entity.Company;
import me.zouabi.versioning.model.repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class CompanyTests {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void createCompany() {
        final var company = Company.builder()
                .name("Test Company")
                .build();

        companyRepository.save(company);

        final var versionId = company.getVersionId();
        Assertions.assertThat(versionId).isNotNull();
        Assertions.assertThat(versionId).isEqualTo(company.getId());
        Assertions.assertThat(versionId.version()).isEqualTo(7);

        Assertions.assertThat(company.getEntityId()).isNotNull();
    }

}
