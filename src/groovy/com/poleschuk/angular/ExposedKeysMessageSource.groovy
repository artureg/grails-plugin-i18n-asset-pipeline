package com.poleschuk.angular

import org.codehaus.groovy.grails.context.support.PluginAwareResourceBundleMessageSource

class ExposedKeysMessageSource extends PluginAwareResourceBundleMessageSource {
  Set getAllProperties(Locale locale) {
    Properties applicationKeys = getMergedProperties(locale).getProperties()
    Properties pluginsKeys = getMergedPluginProperties(locale).getProperties()
    Set resultSet = [] as Set
    resultSet.addAll(applicationKeys)
    resultSet.addAll(pluginsKeys)
    return resultSet
  }
}