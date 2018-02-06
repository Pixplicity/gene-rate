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

package com.pixplicity.generate;

/**
 * Default, empty implementations of the {@link OnFeedbackListener} methods.
 */
public class OnFeedbackAdapter implements OnFeedbackListener {

    public void onFeedbackTapped() {
    }

    public void onRateTapped() {
    }

    public void onRequestDismissed(boolean dontAskAgain) {
    }
}
