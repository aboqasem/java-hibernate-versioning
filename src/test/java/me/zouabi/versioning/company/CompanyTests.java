package me.zouabi.versioning.company;

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
        final var name = "Test Company";
        final var company = Company.builder()
                .name(name)
                .build();

        companyRepository.save(company);

        Assertions.assertThat(company.getName()).isEqualTo(name);
    }

}
