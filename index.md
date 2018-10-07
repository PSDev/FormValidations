FormValidations
===============

[![Build Status](https://ci.psdev.de/job/PSDevFormValidations/badge/icon)](https://ci.psdev.de/job/PSDevFormValidations/) [![Coverage](https://sonar.psdev.de/api/project_badges/measure?project=de.psdev.formvalidations&metric=coverage)](https://sonar.psdev.de/dashboard?id=de.psdev.formvalidations) [![Lines of Code](https://sonar.psdev.de/api/project_badges/measure?project=de.psdev.formvalidations&metric=ncloc)](https://sonar.psdev.de/dashboard?id=de.psdev.formvalidations) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.psdev.formvalidations/formvalidations/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.psdev.formvalidations/formvalidations)

FormValidations is an open source library to validate forms in Android apps.

Available validations
---------------------
* AlphaNumeric - checks if a fields value only contains alpha-numeric characters
* InRange - checks if a fields value is an integer and in a given range (```min < value < max```)
* IsEmail - checks if a fields value is a valid email
* IsNumber - checks if a felds value is a number
* IsPositiveInteger - checks if fields value is an integer and greater than zero (```value > 0```)
* Length - checks if a fields value is a given length (min, max or both)
* NotEmpty - checks if field is not blank

You can easily create your own validations, just implement the ```de.psdev.formvalidations.validations.Validation``` interface.

Error handlers
--------------

* `EditTextErrorHandler` - displays the validation error on the `EditText` using the `setError` method

You can easily create your own error handlers, just implement the ```de.psdev.formvalidations.FormErrorHandler``` interface.

Sample usage
------------

Create validation form and add some validations
```java
final Form mForm = Form.create();
mForm.addField(Field.using(mNameEditText).validate(NotEmpty.build()));
mForm.addField(Field.using(mEmailEditText).validate(NotEmpty.build()).validate(IsEmail.build()));
mForm.addField(Field.using(mAgeEditText).validate(InRange.build(0, 120)));
mForm.errorHandler(new EditTextErrorHandler());
```

Check if the form is valid
```java
private void submit() {
    if (mForm.isValid()) {
        Toast.makeText(this, "Form is valid", Toast.LENGTH_SHORT).show();
    }
}
```

Checkout the sample project for the full usage example.

Download
--------

Download [the latest Release][1] or grab via Maven:

```xml
<dependency>
  <groupId>de.psdev.formvalidations</groupId>
  <artifactId>formvalidations</artifactId>
  <version>1.2.0</version>
  <type>aar</type>
</dependency>
```
or Gradle:
```groovy
implementation 'de.psdev.formvalidations:formvalidations:1.2.0'
```

Sample
------

You can get the latest sample from [Jenkins][2]

Credits
-------

This library is based on [z-validations][3] by [Vitaliy Zasadnyy][4].

Contributors
------------

Thank you to all the contributors of this project, namely:

- [Cristian Hernandez](https://github.com/PSDev/FormValidations/commits?author=devcodaltec)

License
-------

    Copyright 2013 Philip Schiffer

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

    This library incorporates code covered by the following terms:

    The MIT License (MIT)

    Copyright (c) 2013 Vitaliy Zasadnyy

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to
    use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
    the Software, and to permit persons to whom the Software is furnished to do so,
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
    COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
    IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
    CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

[1]: https://github.com/PSDev/FormValidations/releases
[2]: https://ci.psdev.de/job/PSDevFormValidations/lastSuccessfulBuild/artifact/sample/build/outputs/apk/debug/
[3]: https://github.com/zasadnyy/z-validations
[4]: http://about.me/vitaliy.zasadnyy
