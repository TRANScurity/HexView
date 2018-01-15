# HexView Component for Vaadin 7

HexView represents a simple UI component for visualization of binary data in a Hex view with Vaadin Framwork. It provides lazy loading of the displayed data in order to save the client's resources.

![Exemplary screenshot](https://github.com/TRANScurity/HexView/blob/master/HexView/src/main/documentation/images/hexview-example.png)

## Release notes
### 1.0.0

* Read-only hex-view
* Customizable CSS for byte data cells

## Roadmap

The component is used by our [analysis platform](http://platform.transcurity.co/) for executable formats and satisfies the current requirements. In future, it also requires a non-read-only mode. Further features and/or contributions by external users are welcome!

## Issue tracking

Bugs, feature requests or pull requests should be reported via Github's issue tracking system.

## Contributions

Contributions are welcome. But we cannot guarantee that all suggestions will be accepted. If you would like to contribute something then please satisfy the following process:

* Create an issue for your bug fix or feature and discuss it with us in order to make sure that it will be accepted
* Fork this project, implement your changes and write tests
* Send a pull request for the original project
* Comment your issue that your changes are ready for review

## License & Author

The component was initially implemented by [TRANScurity](http://www.transcurity.co/) and is distributed under the GNU LGPL 3.0 (see ``LICENSE`` file)

# Developer Guide
## Getting started

You can import the artifact by:

```xml
<dependency>
    <groupId>com.github.transcurity</groupId>
    <artifactId>HexView</artifactId>
    <version>x.y.z</version>
</dependency>
```

The component can be used in this way:

```java
final byte[] byteData = createResourceBytes();
final HexView hexView = new HexView(byteData);
hexView.setSizeFull();
```

## Features

### Custom CSS for cells

If you want to specify arbitrary CSS for specific data cell ranges you do it this way:

```scss
.peHexStyleDosHeader {
  background: #FF7F66 !important;
}
```

```java
hexView.addCustomStyleRange(0, 64, "peHexStyleDosHeader");
hexView.addCustomStyleRange(0, 2, "peHexStyleDosHeaderEmagic");
hexView.addCustomStyleRange(60, 4, "peHexStyleDosHeaderElfanew");
hexView.addCustomStyleRange(64, 160, "peHexStyleDosStub");
hexView.addCustomStyleRange(224, 248, "peHexStyleNtHeaders");
hexView.addCustomStyleRange(224, 4, "peHexStyleNtHeadersSignature");
```

## Development with Eclipse IDE

Eclipse IDE supports Maven projects by nature. But the basic tests of this project are implemented with help of the Spock Framework. It's based on the Groovy programming language. If you would like to integrate and reuse it, you could look out for ``Groovy for Eclipse`` Plugin.

If you would like to contribute we don't insist on Spock tests! The main point is that you write tests! Feel free to use the convenient framework that fulfills your/our needs! But don't reinvent the wheel and, as far as possible, rely on established frameworks, such as [AssertJ](http://joel-costigliola.github.io/assertj/).

### Theming

Basically, the HexView component obtains the given theme from the Valo engine.

https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
