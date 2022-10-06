package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _04_GeneralTests {

  @ArchTest
  static final ArchRule no_classes_should_access_standard_streams
    = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

  @ArchTest
  static final ArchRule no_classes_should_throw_generic_exceptions
    = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  @ArchTest
  static final ArchRule no_classes_should_use_field_injection
    = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

  @ArchTest
  static final ArchRule no_classes_should_use_jodatime
    = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;

  @ArchTest
  static final ArchRule no_classes_should_use_java_util_logging
    = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
}
