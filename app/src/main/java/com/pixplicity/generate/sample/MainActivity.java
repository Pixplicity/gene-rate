/*
 * Copyright (c) 2017   Mathijs Lagerberg, Pixplicity BV
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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pixplicity.generate.OnFeedbackListener;
import com.pixplicity.generate.Rate;

public class MainActivity extends AppCompatActivity {

    private Rate mRateDialog, mRateBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup root = (ViewGroup) findViewById(R.id.activity_main);
        Button btAction = (Button) findViewById(R.id.bt_action);
        Button btTestDialog = (Button) findViewById(R.id.bt_test_dialog);
        Button btTestBar = (Button) findViewById(R.id.bt_test_bar);
        btAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onActionPerformed();
            }
        });
        btTestDialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onTestDialogClicked();
            }
        });
        btTestBar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onTestBarClicked();
            }
        });

        mRateDialog = new Rate.Builder(this)
                .setTriggerCount(3)
                .setMinimumInstallTime(0)
                .setFeedbackAction(Uri.parse("geo:51.7552289,-87.6350339,674"))
                .build();

        mRateBar = new Rate.Builder(this)
                .setMessage(R.string.please_rate_short)
                .setMinimumInstallTime(0)
                .setFeedbackAction(new OnFeedbackListener() {
                    @Override
                    public void onFeedbackTapped() {
                        Toast.makeText(MainActivity.this, "Meh", Toast.LENGTH_SHORT).show();
                    }
                })
                .setSnackBarParent(root)
                .build();

        Button btInfo = (Button) findViewById(R.id.bt_info);
        btInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://pixplicity.com/?utm_source=android-gene-rate-app&utm_medium=app"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRateDialog.count();
    }

    private void onActionPerformed() {
        if (!mRateDialog.showRequest()) {
            Toast.makeText(this, "Launch the app a few more times", Toast.LENGTH_SHORT).show();
        }
    }

    private void onTestDialogClicked() {
        mRateDialog.test();
    }

    private void onTestBarClicked() {
        mRateBar.test();
    }
}
