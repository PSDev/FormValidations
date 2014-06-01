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

public class IsNumberTest extends BaseValidationTest {

    private IsNumber mIsNumber;

    @Before
    public void setUp() throws Exception {
        mIsNumber = IsNumber.build();
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        final String errorMessage = mIsNumber.getErrorMessage(mActivity);
        assertNotNull(errorMessage);
    }

    @Test
    public void testNumbersAreValid() throws Exception {
        assertTrue(mIsNumber.isValid("1"));
        assertTrue(mIsNumber.isValid("12"));
        assertTrue(mIsNumber.isValid("123"));
    }

    @Test
    public void testTextIsNotValid() throws Exception {
        assertFalse(mIsNumber.isValid("text"));
    }
}
