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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IsEmailTest extends BaseValidationTest {

    private Validation mIsEmail;

    @Before
    public void setUp() throws Exception {
        mIsEmail = IsEmail.build();
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        final String errorMessage = mIsEmail.getErrorMessage(mActivity);
        assertNotNull(errorMessage);
    }

    @Test
    public void testSimpleEmailIsValid() throws Exception {
        assertTrue(mIsEmail.isValid("user@example.org"));
    }

    @Test
    public void testEmailWithPlusIsValid() throws Exception {
        assertTrue(mIsEmail.isValid("user+alias@example.org"));
    }

    @Test
    public void testEmailWithPointsIsValid() throws Exception {
        assertTrue(mIsEmail.isValid("user.name@example.org"));
    }
}
