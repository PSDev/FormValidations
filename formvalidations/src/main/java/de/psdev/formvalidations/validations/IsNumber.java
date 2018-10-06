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
 */

package de.psdev.formvalidations.validations;

import android.content.Context;
import de.psdev.formvalidations.R;

public class IsNumber implements Validation {

    public static IsNumber build() {
        return new IsNumber();
    }

    private IsNumber() {
    }

    @Override
    public String getErrorMessage(final Context context) {
        return context.getString(R.string.formvalidations_numeric);
    }

    @Override
    public boolean isValid(final String value) {
        return value.matches("[0-9]*");
    }
}
