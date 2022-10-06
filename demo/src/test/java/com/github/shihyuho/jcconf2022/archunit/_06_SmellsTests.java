package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _06_SmellsTests {

  @ArchTest
  static final ArchRule methods_with_long_parameter_list =
    ArchRuleDefinition.methods().should(haveParametersAtMost3(3));

  private static ArchCondition<JavaMethod> haveParametersAtMost3(final int maxParameters) {
    return new ArchCondition<>("have parameters at most " + maxParameters) {
      @Override
      public void check(JavaMethod item, ConditionEvents events) {
        if (item.getParameters().size() > maxParameters) {
          events.add(
            SimpleConditionEvent.violated(item,
              "%s have %s parameters".formatted(item.getDescription(), item.getParameters().size()))
          );
        }
      }
    };
  }

  @ArchTest
  static final ArchRule classes_too_large =
    ArchRuleDefinition.classes().should(new ArchCondition<>("contain less than 30 fields") {
      @Override
      public void check(JavaClass item, ConditionEvents events) {
        if (item.getFields().size() > 30) {
          events.add(
            SimpleConditionEvent.violated(item,
              "%s have %s fields".formatted(item.getDescription(), item.getFields().size()))
          );
        }
      }
    });


  @ArchTest
  static final ArchRule long_method =
    ArchRuleDefinition.methods()
      .should(new ArchCondition<>("not have more than 30 code lines") {
        @Override
        public void check(JavaMethod item, ConditionEvents events) {
          var firstLine = item.getSourceCodeLocation().getLineNumber();
          var lastLine = item.getCallsFromSelf().stream().mapToInt(JavaAccess::getLineNumber)
            .max().orElse(firstLine);
          if (lastLine - firstLine > 30) {
            events.add(
              SimpleConditionEvent.violated(item,
                "%s have %s code lines".formatted(item.getDescription(), lastLine - firstLine))
            );
          }
        }
      });

}
