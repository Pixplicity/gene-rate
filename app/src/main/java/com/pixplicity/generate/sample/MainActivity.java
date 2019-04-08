/*
 * Copyright (c) 2017,2018   Mathijs Lagerberg, Pixplicity BV
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pixplicity.generate.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pixplicity.generate.OnFeedbackListener;
import com.pixplicity.generate.Rate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Rate mRateDialogDark, mRateDialogLight, mRateBarDark, mRateBarLight;
    private RadioButton mRbDialogDark, mRbDialogLight, mRbSnackbarDark, mRbSnackbarLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRbDialogDark = findViewById(R.id.rb_dialog_dark);
        mRbDialogLight = findViewById(R.id.rb_dialog_light);
        mRbSnackbarDark = findViewById(R.id.rb_snackbar_dark);
        mRbSnackbarLight = findViewById(R.id.rb_snackbar_light);

        ViewGroup root = findViewById(R.id.activity_main);
        Button btAction = findViewById(R.id.bt_action);
        Button btFake = findViewById(R.id.bt_fake);
        Button btTest = findViewById(R.id.bt_test);
        btAction.setOnClickListener(view -> onActionPerformed(getCurrentRate()));
        btFake.setOnClickListener(view -> onAppLaunched(getCurrentRate()));
        btTest.setOnClickListener(view -> onTestDialogClicked(getCurrentRate()));

        mRateDialogDark = createDialog(false);
        mRateDialogLight = createDialog(true);
        mRateBarDark = createSnackBar(root, false);
        mRateBarLight = createSnackBar(root, true);

        TextView tvMadeBy = findViewById(R.id.tv_made_by);
        tvMadeBy.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.pix_link)));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        TextView tvGitHub = findViewById(R.id.tv_github);
        tvGitHub.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.github_link)));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    @NonNull
    private Rate createDialog(boolean lightTheme) {
        return new Rate.Builder(this)
                .setTriggerCount(3)
                .setMinimumInstallTime(0)
                .setLightTheme(lightTheme)
                .setFeedbackAction(Uri.parse("geo:51.7552289,-87.6350339,674"))
                .build();
    }

    @NonNull
    private Rate createSnackBar(ViewGroup root, boolean lightTheme) {
        return new Rate.Builder(this)
                .setMessage(R.string.please_rate_short)
                .setTriggerCount(3)
                .setMinimumInstallTime(0)
                .setLightTheme(lightTheme)
                .setFeedbackText("Provide feedback...")
                .setFeedbackAction(new OnFeedbackListener() {
                    @Override
                    public void onFeedbackTapped() {
                        Toast.makeText(MainActivity.this, "Meh", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRateTapped() {
                        Toast.makeText(MainActivity.this, "Redirecting to the Play Store...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRequestDismissed(boolean dontAskAgain) {
                        if (dontAskAgain) {
                            Toast.makeText(MainActivity.this, "Ok then, I won't bother you again.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Ok, I'll ask again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setSnackBarParent(root)
                .build();
    }

    private void onActionPerformed(Rate rate) {
        if (!rate.showRequest()) {
            showRemainingCount(rate);
        }
    }

    private void onAppLaunched(Rate rate) {
        rate.count();
        showRemainingCount(rate);
    }

    private Rate getCurrentRate() {
        if (mRbDialogDark.isChecked()) {
            return mRateDialogDark;
        } else if (mRbDialogLight.isChecked()) {
            return mRateDialogLight;
        } else if (mRbSnackbarDark.isChecked()) {
            return mRateBarDark;
        } else if (mRbSnackbarLight.isChecked()) {
            return mRateBarLight;
        } else {
            throw new IllegalStateException("No checkboxes checked");
        }
    }

    @SuppressLint("ShowToast")
    private synchronized void showRemainingCount(Rate rate) {
        int count = (int) rate.getRemainingCount();
        String message = getResources().getQuantityString(R.plurals.toast_x_more_times, count, count);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onTestDialogClicked(Rate rate) {
        rate.test();
    }

}
