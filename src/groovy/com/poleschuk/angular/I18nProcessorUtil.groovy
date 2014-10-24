package com.poleschuk.angular

import grails.util.Holders
import org.codehaus.groovy.grails.context.support.PluginAwareResourceBundleMessageSource
import org.springframework.context.support.ReloadableResourceBundleMessageSource

import java.util.regex.Matcher
import java.util.regex.Pattern

class I18nProcessorUtil {

  static String getModuleName(File file) {
    def config = Holders.config

    String moduleSeparator = config?.grails?.assets?.angular?.moduleSeparator ?: '.'
    String templateRoot = config?.grails?.assets?.angular?.templateRoot ?: 'i18n'

    def pathParts = file.path.tokenize(File.separator)
    int assetRootIndex = pathParts.indexOf('grails-app') + 1
    def relativePathParts = pathParts[assetRootIndex + 2..pathParts.size() - 1] - file.name - templateRoot

    relativePathParts.collect { toCamelCase it }.join(moduleSeparator)
  }

  static String propToMessages(String locale) {
    ExposedKeysMessageSource messageSource = Holders.applicationContext.getBean("messageSource")
    def messages = ""
    Set<Properties> holders = messageSource.getAllProperties(Locale.forLanguageTag(locale))
    for (Properties props : holders) {
      Set keys = props.keySet()
      for (String key : keys) {
        messages += "\"${key}\":\"${props.get(key).replaceAll("\"", "\\\"")}\","
      }
    }
    if (messages.length() > 0) {
      "{" + messages.substring(0, messages.length() - 1) + "}"
    } else {
      "{}"
    }
  }

  static String processContent(String moduleName, String templateName, String content) {
    Pattern p = Pattern.compile(/%Locale\((.*)\)%/);
    Matcher m = p.matcher(content);
    StringBuffer s = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(s, propToMessages(m.group(1)));
    }
    m.appendTail(s);
    s
  }

  static String toCamelCase(String input) {
    input = input?.toLowerCase()

    final String separator = "-"

    if (input?.contains('_')) {
      input = input.replace('_', separator)
    }

    if (!input || !input.contains(separator)) {
      return input
    }

    def result = new StringBuilder()
    input.split(separator).eachWithIndex { part, index ->

      if (index > 0 && part?.length() != 0) {
        result.append(part.substring(0, 1).toUpperCase() + part.substring(1))
      } else {
        result.append(part ?: "")
      }
    }

    result.toString()
  }

}
