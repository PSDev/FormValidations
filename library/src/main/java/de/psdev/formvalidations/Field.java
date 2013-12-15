/*
 * Copyright 2013 Philip Schiffer
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  This file incorporates code covered by the following terms:
 *
 *     The MIT License (MIT)
 *
 *     Copyright (c) 2013 Vitaliy Zasadnyy
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy of
 *     this software and associated documentation files (the "Software"), to deal in
 *     the Software without restriction, including without limitation the rights to
 *     use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *     the Software, and to permit persons to whom the Software is furnished to do so,
 *     subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *     FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *     COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *     IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *     CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.psdev.formvalidations;

import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

import de.psdev.formvalidations.validations.Validation;

public class Field {

    private final EditText mEditText;
    private final List<Validation> mValidations;

    public static Field using(final EditText editText) {
        if(editText == null) {
            throw new IllegalArgumentException("EditText field may not be null");
        }
        return new Field(editText);
    }

    private Field(final EditText editText) {
        mEditText = editText;
        mValidations = new LinkedList<Validation>();
    }

    //

    public Field validate(final Validation what) {
        mValidations.add(what);
        return this;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public boolean isValid() throws FieldValidationException {
        for (final Validation validation : mValidations) {
            if (!validation.isValid(mEditText.getText().toString())) {
                final String errorMessage = validation.getErrorMessage(mEditText.getContext());
                throw new FieldValidationException(errorMessage, mEditText);
            }
        }
        return true;
    }

}
