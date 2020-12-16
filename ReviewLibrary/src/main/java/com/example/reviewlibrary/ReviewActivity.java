package com.example.reviewlibrary;

import android.app.Activity;
import android.widget.Toast;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
public class ReviewActivity{
    public static ReviewManager manager;
    public static ReviewInfo reviewInfo;
    public static void CallInAppReview(Activity activity) {
        manager = ReviewManagerFactory.create(activity);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
                StartInAppTask(activity);
            } else {
            }
        });
    }

    public static void StartInAppTask(Activity activity) {
        if (reviewInfo != null) {
            Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
            flow.addOnCompleteListener(task -> {
                Toast.makeText(activity.getApplicationContext(), "review successfull", Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(activity.getApplicationContext(), "review unsuccessfully try again", Toast.LENGTH_SHORT).show();
        }
    }
}