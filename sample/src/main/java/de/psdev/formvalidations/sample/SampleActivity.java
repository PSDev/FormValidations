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
 *
 *  This file incorporates code covered by the following terms:
 *
 *     The MIT License (MIT)
 *
 *     Copyright (c) 2013 Vitaliy Zasadnyy
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy of
 *     this software and associated documentation files (the "Software"), to deal in
 *     the Software without restriction, including without limitation the rights to
 *     use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *     the Software, and to permit persons to whom the Software is furnished to do so,
 *     subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *     FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *     COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *     IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *     CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.psdev.formvalidations.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.psdev.formvalidations.EditTextErrorHandler;
import de.psdev.formvalidations.Field;
import de.psdev.formvalidations.Form;
import de.psdev.formvalidations.FormUtils;
import de.psdev.formvalidations.validations.InRange;
import de.psdev.formvalidations.validations.IsEmail;
import de.psdev.formvalidations.validations.NotEmpty;

public class SampleActivity extends Activity {

    private static final String GITHUB_PAGE = "https://github.com/PSDev/FormValidations";

    // View injections
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.email)
    EditText mEmail;
    @Bind(R.id.age)
    EditText mAge;
    @Bind(R.id.submit)
    Button mSubmit;

    // Form used for validation
    private Form mForm;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        initFields();
        initValidationForm();
        initCallbacks();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                openGitHub();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initFields() {
        ButterKnife.bind(this);
    }

    private void initValidationForm() {
        mForm = Form.create();
        mForm.addField(Field.using(mName).validate(NotEmpty.build()));
        mForm.addField(Field.using(mEmail).validate(NotEmpty.build()).validate(IsEmail.build()));
        mForm.addField(Field.using(mAge).validate(InRange.build(0, 120)));
        mForm.errorHandler(new EditTextErrorHandler());
    }

    private void initCallbacks() {
        mAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(final TextView view, final int actionId, final KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    submit();
                    return true;
                }
                return false;
            }

        });

        mSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                submit();
            }
        });
    }

    private void submit() {
        FormUtils.hideKeyboard(this, mAge);
        if (mForm.isValid()) {
            Toast.makeText(this, R.string.sample_activity_form_is_valid, Toast.LENGTH_LONG).show();
        }
    }

    private void openGitHub() {
        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_PAGE));
        startActivity(browserIntent);
    }

}
