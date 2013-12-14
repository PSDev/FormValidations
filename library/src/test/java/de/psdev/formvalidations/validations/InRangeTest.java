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

import org.junit.Test;

import static org.junit.Assert.*;

public class InRangeTest extends BaseValidationTest {

    @Test
    public void testGetErrorMessage() throws Exception {
        final Validation validation = InRange.build(2, 4);
        final String errorMessage = validation.getErrorMessage(mActivity);
        assertNotNull(errorMessage);
        assertTrue(errorMessage.contains(Integer.toString(2)));
        assertTrue(errorMessage.contains(Integer.toString(4)));
    }

    @Test
    public void testInRangeIsExclusive() throws Exception {
        final Validation validation = InRange.build(2, 4);
        assertFalse(validation.isValid("2"));
        assertTrue(validation.isValid("3"));
        assertFalse(validation.isValid("4"));
    }

    @Test
    public void testTextIsNotValid() throws Exception {
        final Validation validation = InRange.build(2, 4);
        assertFalse(validation.isValid("test"));
    }
}
