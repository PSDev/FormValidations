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
import de.psdev.formvalidations.validations.IsEmail;
import de.psdev.formvalidations.validations.NotEmpty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class FormTest {
    private Activity mActivity;
    private Form mForm;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.buildActivity(Activity.class).create().get();
        mForm = Form.create();
    }

    @Test
    public void testFormWithOneFieldIsValid() throws Exception {
        final EditText editText = new EditText(mActivity);
        mForm.addField(Field.using(editText).validate(NotEmpty.build()));
        assertFalse(mForm.isValid());
        editText.setText("Hello");
        assertTrue(mForm.isValid());
    }

    @Test
    public void testFormWithMultipleFieldsIsValid() throws Exception {
        final EditText fieldOne = new EditText(mActivity);
        final EditText fieldTwo = new EditText(mActivity);
        mForm.addField(Field.using(fieldOne).validate(NotEmpty.build()));
        mForm.addField(Field.using(fieldTwo).validate(NotEmpty.build()).validate(IsEmail.build()));
        assertFalse(mForm.isValid());
        fieldOne.setText("Hello");
        assertFalse(mForm.isValid());
        fieldTwo.setText("user@example.org");
        assertTrue(mForm.isValid());
    }

    @Test
    public void testFormWithErrorHandler() throws Exception {
        final EditText fieldOne = new EditText(mActivity);
        final AtomicBoolean resetErrorCalled = new AtomicBoolean(false);
        final AtomicBoolean handleErrorCalled = new AtomicBoolean(false);
        mForm.addField(Field.using(fieldOne).validate(NotEmpty.build()));
        mForm.errorHandler(new FormErrorHandler() {
            @Override
            public void handleError(@Nonnull final FieldValidationException e) {
                handleErrorCalled.set(true);
            }

            @Override
            public void resetError(@Nonnull final Field field) {
                resetErrorCalled.set(true);
            }
        });
        assertFalse(mForm.isValid());
        assertTrue(handleErrorCalled.get());
        assertTrue(resetErrorCalled.get());
    }

}
