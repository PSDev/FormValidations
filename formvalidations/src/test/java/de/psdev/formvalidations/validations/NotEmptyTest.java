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

import org.junit.Before;
import org.junit.Test;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

public class NotEmptyTest extends BaseValidationTest {

    private Validation mNotEmpty;

    @Before
    public void setUp() throws Exception {
        mNotEmpty = NotEmpty.build();
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        final String errorMessage = mNotEmpty.getErrorMessage(mActivity);
        assertNotNull(errorMessage);
    }

    @Test
    public void testAnyTextIsValid() throws Exception {
        assertTrue(mNotEmpty.isValid("anytext"));
    }

    @Test
    public void testNoTextIsNotValid() throws Exception {
        assertFalse(mNotEmpty.isValid(""));
    }
}
