package com.github.shihyuho.jcconf2022.archunit;


import com.tngtech.archunit.core.domain.JavaCall;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packagesOf = MyApplication.class, importOptions = {
  DoNotIncludeTests.class,
  DoNotIncludeJars.class
})
class _06_SmellsTests {


  @ArchTest
  static ArchRule long_parameters_list = ArchRuleDefinition.methods()
    .should(new ArchCondition<>("have parameters at most 3") {
      @Override
      public void check(JavaMethod item, ConditionEvents events) {
        if (item.getParameters().size() > 3) {
          events.add(
            SimpleConditionEvent.violated(item,
              "%s have %s parameters".formatted(item.getDescription(), item.getParameters().size()))
          );
        }
      }
    });

  @ArchTest
  static ArchRule large_class = ArchRuleDefinition.classes()
    .should(new ArchCondition<>("contains less than 30 fields") {
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
  static ArchRule long_method = ArchRuleDefinition.methods()
    .should(notHaveCodeLinesMoreThan(30));

  private static ArchCondition<JavaMethod> notHaveCodeLinesMoreThan(final int maxLines) {
    return new ArchCondition<>("not have no more than %s code lines".formatted(maxLines)) {
      @Override
      public void check(JavaMethod item, ConditionEvents events) {
        var first = item.getSourceCodeLocation().getLineNumber();
        var last = item.getCallsFromSelf().stream().mapToInt(JavaCall::getLineNumber).max()
          .orElse(first);
        var lines = last - first + 1;
        if (lines > maxLines) {
          events.add(
            SimpleConditionEvent.violated(
              item,
              "%s have %s code lines".formatted(item.getDescription(), lines)
            )
          );
        }
      }
    };
  }

}
