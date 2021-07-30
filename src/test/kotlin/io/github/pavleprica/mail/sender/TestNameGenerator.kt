package io.github.pavleprica.mail.sender

import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.DisplayNameGenerator.Standard

import java.lang.reflect.Method;


class TestNameGenerator : Standard() {
    override fun generateDisplayNameForClass(testClass: Class<*>?): String {
        return replaceCamelCase(super.generateDisplayNameForClass(testClass))
    }

    override fun generateDisplayNameForNestedClass(nestedClass: Class<*>?): String {
        return replaceCamelCase(super.generateDisplayNameForNestedClass(nestedClass))
    }

    override fun generateDisplayNameForMethod(testClass: Class<*>?, testMethod: Method): String {
        return if (testMethod.name.contains("_")) {
            replaceUnderScores(testMethod.name) +
                    DisplayNameGenerator.parameterTypesAsString(testMethod)
        } else {
            replaceCamelCase(testMethod.name) +
                    DisplayNameGenerator.parameterTypesAsString(testMethod)
        }
    }

    private fun replaceCamelCase(camelCase: String): String {
        val result = StringBuilder()
        result.append(camelCase[0])
        for (i in 1 until camelCase.length) {
            if (Character.isUpperCase(camelCase[i])) {
                result.append(' ')
                result.append(camelCase[i].lowercaseChar())
            } else {
                result.append(camelCase[i])
            }
        }
        return result.toString()
    }

    private fun replaceUnderScores(underscore: String): String {
        return underscore.replace("_", " ")
    }
}