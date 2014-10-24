import asset.pipeline.AssetHelper
import com.poleschuk.angular.ExposedKeysMessageSource
import com.poleschuk.angular.I18nAssetFile
import org.codehaus.groovy.grails.context.support.PluginAwareResourceBundleMessageSource

class I18nAssetPipelineGrailsPlugin {

  def version = "1.0"
  def grailsVersion = "2.0 > *"

  def watchedResources = ['file:./grails-app/views/_fields/**']
  def observe = ['domainClass']

  def pluginExcludes = [
    "grails-app/assets/**",
    "grails-app/domain/**",
    "grails-app/views/**"
  ]

  def title = "i18n Asset-Pipeline Plugin"
  def author = "Arthur Paliaščuk"
  def authorEmail = "p.artureg@gmail.com"
  def description = ""
  def documentation = "http://github.com/artureg/grails-plugin-i18n-asset-pipeline"

  def license = "APACHE"
  def issueManagement = [system: "GITHUB", url: "http://github.com/craigburke/angular-i18n-asset-pipeline/issues"]
  def scm = [url: "http://github.com/craigburke/angular-i18n-asset-pipeline"]

  def loadAfter = ['fields']

  def doWithSpring = {
    def beanconf = springConfig.getBeanConfig('messageSource')
    def beandef = beanconf ? beanconf.beanDefinition : springConfig.getBeanDefinition('messageSource')
    if (beandef?.beanClassName == PluginAwareResourceBundleMessageSource.class.canonicalName) {
      beandef.beanClassName = ExposedKeysMessageSource.class.canonicalName
    }
  }

  def doWithDynamicMethods = { ctx ->
    AssetHelper.assetSpecs << I18nAssetFile
  }

  def onChange = { event ->

  }


}
