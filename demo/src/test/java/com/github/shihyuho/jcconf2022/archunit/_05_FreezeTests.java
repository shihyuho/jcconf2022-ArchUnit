package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.freeze.FreezingArchRule;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _05_FreezeTests {

  @ArchTest
  static final ArchRule my_frozen_rule
    = FreezingArchRule.freeze(
    ArchRuleDefinition.classes().should()
      .resideOutsideOfPackages("..jcconf2022..")
  );
}
