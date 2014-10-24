package com.poleschuk.angular

import asset.pipeline.AbstractAssetFile

class I18nAssetFile extends AbstractAssetFile {

  static final String contentType = 'application/javascript'
  static extensions = ['i18n']
  static final String compiledExtension = 'js'

  static processors = [I18nProcessor]

  String directiveForLine(String line) {
    return null
  }


}
