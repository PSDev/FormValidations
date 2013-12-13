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

package de.psdev.formvalidations.validations;


import android.content.Context;
import de.psdev.formvalidations.R;

public class Length extends BaseValidation {

    private final int mMinLength;
    private final int mMaxLength;

    public static Validation min(final int minLength) {
        if(minLength <= 0) {
            throw new IllegalArgumentException("minLength must be positive");
        }
        return new Length(minLength, -1);
    }

    public static Validation max(final int maxLength) {
        if(maxLength <= 0) {
            throw new IllegalArgumentException("maxLength must be positive");
        }
        return new Length(-1, maxLength);
    }

    public static Validation range(final int minLength, final int maxLength) {
        if(minLength <= 0) {
            throw new IllegalArgumentException("minLength must be positive");
        }
        if(maxLength <= 0) {
            throw new IllegalArgumentException("maxLength must be positive");
        }
        return new Length(minLength, maxLength);
    }

    private Length(final int minLength, final int maxLength) {
        mMinLength = minLength;
        mMaxLength = maxLength;
    }

    @Override
    public String getErrorMessage(final Context context) {
        if (mMinLength != -1 && mMaxLength != -1) {
            return context.getString(R.string.formvalidations_length_range, mMinLength, mMaxLength);
        } else if (mMinLength == -1 && mMaxLength != -1) {
            return context.getString(R.string.formvalidations_length_max, mMaxLength);
        } else if (mMinLength != -1) {
            return context.getString(R.string.formvalidations_length_min, mMinLength);
        } else {
            throw new IllegalStateException("Cannot happen");
        }
    }

    @Override
    public boolean isValid(final String text) {
        if(text == null) {
            return false;
        }
        if(mMinLength > 0 && mMaxLength > 0) {
            return text.length() > mMinLength && text.length() < mMaxLength;
        }
        if(mMinLength > 0 && mMaxLength < 0) {
            return text.length() > mMinLength;
        }
        if(mMinLength < 0 && mMaxLength > 0) {
            return text.length() < mMaxLength;
        }
        return false;
    }
}
