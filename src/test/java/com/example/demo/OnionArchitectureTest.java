package com.example.demo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = { "com.example.demo" })
public class OnionArchitectureTest {
    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..service..")
            .applicationServices("..application..", "..config..")
            .adapter("persistence", "..repository..")
            .adapter("rest", "..rest..");
}
