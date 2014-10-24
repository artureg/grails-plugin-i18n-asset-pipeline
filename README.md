grails-plugin-i18n-asset-pipeline
=================================

The Grails `grails-plugin-i18n-asset-pipeline` is a plugin that provides i18n map precompiler support to the asset-pipeline plugin.

For more information on how to use asset-pipeline, visit [here](http://www.github.com/bertramdev/asset-pipeline).

## Getting started
Add the plugin to your **BuildConfig.groovy**:
```groovy
plugins {
		compile ":i18n-asset-pipeline:0.11"
}
```
Make sure your templates are contained within a **i18n** folder and have the file extension **.i18n** 

## How it works

This plugin replace the locale labels in i18n files to dictionary with key and value from messages*.properties

For example a file located at

```
/grails-app/assets/javascripts/i18n/dictionary.i18n
```
with content 

```javascript
var _i18n = {
    EN: %Locale(default)%,
    RU: %Locale(ru)%,
    LT: %Locale(lt)%
}
```

Will generate javascript like this:
```
/grails-app/assets/javascripts/i18n/dictionary.js
```
with content for example
```javascript
var _i18n = { 
    EN: {"default.paginate.prev":"Previous","default.paginate.next":"Next"},
    RU: {"default.paginate.prev":"Назад","default.paginate.next":"Вперёд"},
    LT: {"default.paginate.prev":"Ankstesnis","default.paginate.next":"Kitas"}
}
```
