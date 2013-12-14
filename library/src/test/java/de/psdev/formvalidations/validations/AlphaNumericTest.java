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

import static org.junit.Assert.*;


public class AlphaNumericTest extends BaseValidationTest {

    private AlphaNumeric mAlphaNumeric;

    @Before
    public void setUp() throws Exception {
        mAlphaNumeric = AlphaNumeric.build();
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        final String errorMessage = mAlphaNumeric.getErrorMessage(mActivity);
        assertNotNull(errorMessage);
    }

    @Test
    public void testNumbersAreValid() throws Exception {
        assertTrue(mAlphaNumeric.isValid("123"));
    }

    @Test
    public void testAsciiTextIsValid() throws Exception {
        assertTrue(mAlphaNumeric.isValid("test"));
    }

    @Test
    public void testNumbersAndAsciiTextAreValid() throws Exception {
        assertTrue(mAlphaNumeric.isValid("test123"));
    }

    @Test
    public void testSpecialCharsAreNotValid() throws Exception {
        assertFalse(mAlphaNumeric.isValid("ä"));
        assertFalse(mAlphaNumeric.isValid("ö"));
        assertFalse(mAlphaNumeric.isValid("ü"));
        assertFalse(mAlphaNumeric.isValid("ß"));
    }
}
