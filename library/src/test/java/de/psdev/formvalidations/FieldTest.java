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
import de.psdev.formvalidations.validations.NotEmpty;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

public class FieldTest extends BaseTest {

    private Activity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFieldCreationResultsInException() throws Exception {
        Field.using(null);
    }

    @Test(expected = FieldValidationException.class)
    public void testEmptyFieldIsNotValid() throws Exception {
        final EditText editText = new EditText(mActivity);
        final Field field = Field.using(editText);
        field.validate(NotEmpty.build());
        assertFalse(field.isValid());
        editText.setText("text");
        assertTrue(field.isValid());
    }

    @Test
    public void testNotEmptyFieldIsValid() throws Exception {
        final EditText editText = new EditText(mActivity);
        final Field field = Field.using(editText);
        field.validate(NotEmpty.build());
        editText.setText("text");
        assertTrue(field.isValid());
    }

    @Test
    public void testGetEditText() throws Exception {
        final EditText editText = new EditText(mActivity);
        final Field field = Field.using(editText);
        assertSame(editText, field.getEditText());
    }
}
