# JViewModel
is a library, inspired by C# WPFs ViewModel patterns. It reduces the boilerplate needed to implement [Model/View/ViewModel (MVVM)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) in Java, with Java Swing.

## ViewModelProperty
is a variable, which announces it's changes to all subscribers. It is intended to wrap around a Model, or better, a [JavaBeans](https://en.wikipedia.org/wiki/JavaBeans).

## Java Swing MVVM
A collection of java swing elements, which are bindable with values through ViewModelProperties.

## RevertableProperty
is an extension of ViewModelProperty, which makes them revertable. It tracks all changes with optional timestamps, so you can let the user revert their changes.

## ViewModelLocator and DependencyInjector
Decouples View and ViewModel even further with a ViewModel Locator, which gives you the ViewModel for each View through the constructor. Also you can pass functionality through the constructor, like logging. 

## ViewModel Logger
Logs the changes of given ViewModel. Also has an implementation of [Log4j](https://logging.apache.org/log4j/2.x/).
