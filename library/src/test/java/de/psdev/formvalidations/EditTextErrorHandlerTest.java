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

package de.psdev.formvalidations;

import android.app.Activity;
import android.widget.EditText;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class EditTextErrorHandlerTest {

    private static final String ERROR_MESSAGE = "Error message";

    private EditText mEditText;
    private EditTextErrorHandler mEditTextErrorHandler;

    @Before
    public void setUp() throws Exception {
        final Activity activity = Robolectric.buildActivity(Activity.class).create().get();
        mEditText = new EditText(activity);
        mEditTextErrorHandler = new EditTextErrorHandler();
    }

    @Test
    public void testHandleAndResetError() throws Exception {
        assertNull(mEditText.getError());
        final FieldValidationException e = new FieldValidationException(ERROR_MESSAGE, mEditText);
        mEditTextErrorHandler.handleError(e);
        assertNotNull(mEditText.getError());
        assertEquals(ERROR_MESSAGE, mEditText.getError());
        mEditTextErrorHandler.resetError(Field.using(mEditText));
        assertNull(mEditText.getError());
    }
}
