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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LengthTest extends BaseValidationTest {

    @Test
    public void testMin() throws Exception {
        final Validation validation = Length.min(2);
        assertFalse(validation.isValid("a"));
        assertTrue(validation.isValid("ab"));
    }

    @Test
    public void testMax() throws Exception {
        final Validation validation = Length.max(3);
        assertTrue(validation.isValid("abc"));
        assertFalse(validation.isValid("abcd"));
    }

    @Test
    public void testRange() throws Exception {
        final Validation validation = Length.range(2, 4);
        assertFalse(validation.isValid("a"));
        assertTrue(validation.isValid("ab"));
        assertTrue(validation.isValid("abc"));
        assertTrue(validation.isValid("abcd"));
        assertFalse(validation.isValid("abcde"));
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        // Min
        final Validation minValidation = Length.min(2);
        final String minValidationErrorMessage = minValidation.getErrorMessage(mActivity);
        assertNotNull(minValidationErrorMessage);
        assertTrue(minValidationErrorMessage.contains("2"));
        // Max
        final Validation maxValidation = Length.max(5);
        final String maxValidationErrorMessage = maxValidation.getErrorMessage(mActivity);
        assertNotNull(maxValidationErrorMessage);
        assertTrue(maxValidationErrorMessage.contains("5"));
        // Range
        final Validation rangeValidation = Length.range(2, 5);
        final String rangeValidationErrorMessage = rangeValidation.getErrorMessage(mActivity);
        assertNotNull(rangeValidationErrorMessage);
        assertTrue(rangeValidationErrorMessage.contains("2"));
        assertTrue(rangeValidationErrorMessage.contains("5"));
    }

    @Test
    public void testNullIsNotValid() throws Exception {
        final Validation validation = Length.range(2, 4);
        assertFalse(validation.isValid(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMinValueIsNotPossible() throws Exception {
        Length.min(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMaxValueIsNotPossible() throws Exception {
        Length.max(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMinValueRangeIsNotPossible() throws Exception {
        Length.range(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMaxValueRangeIsNotPossible() throws Exception {
        Length.range(1, -1);
    }
}
