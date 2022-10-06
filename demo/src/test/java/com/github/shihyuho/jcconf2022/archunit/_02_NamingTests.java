package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _02_NamingTests {

  @ArchTest
  static ArchRule controllers_should_have_name_ending_with_controller
    = ArchRuleDefinition.classes()
    .that().areAnnotatedWith(Controller.class)
    .should().haveSimpleNameEndingWith(Controller.class.getSimpleName());

  @ArchTest
  static ArchRule services_should_have_name_ending_with_service
    = ArchRuleDefinition.classes()
    .that().areAnnotatedWith(Service.class)
    .should().haveSimpleNameEndingWith(Service.class.getSimpleName());

  @ArchTest
  static ArchRule repositories_should_have_name_ending_with_dao
    = ArchRuleDefinition.classes()
    .that().implement(Repository.class)
    .should().haveSimpleNameEndingWith("Dao");
}
