# JViewModel [![codecov](https://codecov.io/gh/terrasearch/JViewModel/branch/master/graph/badge.svg?token=5Y5DPBIOES)](https://codecov.io/gh/terrasearch/JViewModel) ![Java CI with Gradle](https://github.com/terrasearch/JViewModel/workflows/Java%20CI%20with%20Gradle/badge.svg)
is a library, inspired by C# WPFs ViewModel patterns. It reduces the boilerplate needed to implement [Model/View/ViewModel (MVVM)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) in Java, with Java Swing. It simplifies already existing elements, which helps with Swing MVVM infrastructure, at the cost of functionality (for now).

##  Property
is a variable, which announces it's changes to all subscribers. It is intended to wrap around a Model, or better, a [JavaBeans](https://en.wikipedia.org/wiki/JavaBeans).

## Java Swing MVVM
A collection of java swing elements, which are bindable with values through ViewModelProperties.

## RevertableProperty
is an extension of ViewModelProperty, which makes them revertable. It tracks all changes with optional timestamps, so you can let the user revert their changes.

## ViewModelLocator and DependencyInjector
Decouples View and ViewModel even further with a ViewModel Locator, which gives you the ViewModel for each View through the constructor. Also you can pass functionality through the constructor, like logging. 

## ViewModel Logger
Logs the changes of given ViewModel. Also has an implementation of [Log4j](https://logging.apache.org/log4j/2.x/).

# Tasklist
- [x] Property
- [ ] Java Swing MVVM
  - [x] Textfields
  - [ ] Buttons
  - [ ] CheckBox
  - [ ] ComboBox
  - [ ] List
  - [ ] Spinner
  - [ ] RadioButton
  - [ ] Slider
  - [ ] ProgressBar
- [ ] Data Abstracted List
- [ ] RevertableProperty
- [ ] ViewModelLocator and DependencyInjector
- [ ] ViewModel Logger
- [ ] ViewModel Logger Log4j implementation
