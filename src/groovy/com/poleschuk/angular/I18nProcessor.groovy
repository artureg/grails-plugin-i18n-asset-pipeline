package com.poleschuk.angular

import asset.pipeline.AssetCompiler
import static com.poleschuk.angular.I18nProcessorUtil.*

class I18nProcessor {

  I18nProcessor(AssetCompiler precompiler) { }

  def process(input, assetFile) {
    File file = assetFile.file

    String moduleName = getModuleName file
    processContent(moduleName, file.name, input.toString())
  }



}
