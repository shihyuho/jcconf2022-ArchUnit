package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _03_ThreeTierTests {

  @ArchTest
  static final ArchRule three_tier_layered = Architectures.layeredArchitecture()
    .consideringAllDependencies()
    .layer("Presentation").definedBy("..controller..")
    .layer("Service").definedBy("..service..")
    .layer("DataAccess").definedBy("..repository..")

    .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
    .whereLayer("Service").mayOnlyBeAccessedByLayers("Presentation")
    .whereLayer("DataAccess").mayOnlyBeAccessedByLayers("Service");
}
