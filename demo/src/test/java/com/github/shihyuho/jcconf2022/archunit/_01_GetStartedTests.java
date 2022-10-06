package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _01_GetStartedTests {

  @ArchTest
  static final ArchRule my_rule
    = ArchRuleDefinition.classes().should()
    .resideInAPackage("com.github.shihyuho.jcconf2022.archunit..");
}
